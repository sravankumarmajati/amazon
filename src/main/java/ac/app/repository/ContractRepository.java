package ac.app.repository;

import cn.hyperchain.sdk.common.utils.ByteUtil;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import ac.app.base.constant.Code;
import ac.app.base.exceptions.DeployException;
import ac.app.base.exceptions.InvokeException;
import ac.app.base.exceptions.SignException;
import ac.app.util.ContractUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractRepository {

    private Logger logger = Logger.getLogger(ContractRepository.class);

    private ProviderManager providerManager = null;

    private ContractService contractService = null;

    private ContractUtil contractUtil;

    private static String DEFAULT_URL = "localhost:9999";

    @Autowired public ContractRepository(ContractUtil contractUtil) {
        try {
            DefaultHttpProvider defaultHttpProvider = new DefaultHttpProvider.Builder().setUrl(DEFAULT_URL).build();
            providerManager = ProviderManager.createManager(defaultHttpProvider);
            contractService = ServiceManager.getContractService(providerManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.contractUtil = contractUtil;
        contractUtil.init();
    }

    /**
     *
     * @param tx
     * @param contractName
     * @param persist
     * @return
     * @throws SignException
     * @throws DeployException
     */
    public String deploy(Transaction tx, String contractName , boolean persist) throws SignException, DeployException, RequestException {
        if (tx.getSignature().equals("")) {
            throw new SignException(Code.TX_UNSIGNED, Code.TX_UNSIGNED.getMsg());
        }
        ReceiptResponse deployReceipt = contractService.deploy(tx).send().polling();
        if (deployReceipt.getCode() == 0 && deployReceipt.getContractAddress() != null) {
            logger.info("deploy Success");
            String addr = deployReceipt.getContractAddress();
            contractUtil.setContractAddress(contractName, addr, persist);
            return addr;
        } else {
            throw new DeployException(Code.DEPLOY_FAILED, Code.DEPLOY_FAILED.getMsg());
        }
    }

    /**
     *
     * @param tx
     * @return
     * @throws SignException
     * @throws InterruptedException
     * @throws InvokeException
     */
    public String invoke(Transaction tx) throws SignException, InvokeException, RequestException {
        if (tx.getSignature().equals("")) {
            throw new SignException(Code.TX_UNSIGNED, Code.TX_UNSIGNED.getMsg());
        }

        ReceiptResponse receipt = contractService.invoke(tx).send().polling();
        if (receipt.getCode() == 0) {
            logger.info("invoke Success");
            // TODO change here no need to decode
            return new String(ByteUtil.toBytes(receipt.getRet()));
        } else {
            throw new InvokeException(Code.CONTRACT_INVOKE_ERROR, Code.CONTRACT_INVOKE_ERROR.getMsg());
        }
    }
     /**
     *
     * @param contractName
     * @param persist_first
     * @return
     */
    public String queryAddress(String contractName, boolean persist_first){
       return contractUtil.getContractAddress(contractName, persist_first);
    }

    /**
     *
     * @param accountName
     * @return
     */
    public String queryAccountJson(String accountName){
        return contractUtil.getDefaultAccountJson();
    }

    public String queryAccountJsonPwd(String accountName){
        return contractUtil.getDefaultAccountJsonPwd();
    }

    public String queryContractName(){
        return contractUtil.getContractName();
    }

    public String queryContractJarName() {
        return contractUtil.getContractJarName();
    }

}
