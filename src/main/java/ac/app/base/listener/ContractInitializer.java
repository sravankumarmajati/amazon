package ac.app.base.listener;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.transaction.Transaction;
import ac.app.repository.ContractRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractInitializer {

    Logger logger = Logger.getLogger(ContractInitializer.class);


    @Autowired
    ContractRepository contractRepository;

    public void init(){
        try {
            logger.info(contractRepository.queryContractName());
            logger.info(contractRepository.queryContractName());
            logger.info(contractRepository.queryAccountJson(""));
            logger.info(contractRepository.queryAccountJsonPwd(""));

            Account account = new Account(contractRepository.queryAccountJson(""));
            Transaction deployTx = new Transaction.HVMBuilder(account.getAddress()).deploy("contract/" + contractRepository.queryContractJarName()).build();
            String address = contractRepository.deploy(deployTx, contractRepository.queryContractName(), true);
            logger.info("contract address is " +  address);
           } catch (Exception e) {// TODO specific exception type
            e.printStackTrace();
        }

    }
}
