package ac.app.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.hyperchain.sdk.account.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContractUtil {
    private Map<String, String> memCAddrs = new HashMap<>();

    private Properties prop = new Properties();


    @Value("${dev.contract.name}")
    public String ContractName;

    @Value("${dev.contract.jarname}")
    public String ContractJarName;

    @Value("${dev.contract.prop}")
    public String contractPropPath;

    @Value("${dev.contract.account}")
    public String defaultAccountJson;

    @Value("${dev.contract.accountpwd}")
    public String defaultAccountJsonPwd;

    public Account defaultAccount = null;

    private InputStream propReader = null;
//    private FileWriter propWriter = null;

    public void init(){
        try {
           propReader = ContractUtil.class.getClassLoader().getResourceAsStream(contractPropPath);
//           propWriter =  new FileWriter(new File(contractPropPath));
           defaultAccount = new Account(defaultAccountJson);
           prop.load(propReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param key
     * @param address
     * @param persist
     * @return
     */
    public void setContractAddress(String key, String address, boolean persist) {
        memCAddrs.put(key, address);
        if (persist) {
            prop.setProperty(key, address);
        }
//        try {
//            prop.store(propWriter, "Contract Address properties");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @param key
     * @param persist_first
     * @return
     */
    public String getContractAddress(String key, boolean persist_first) {
        String val = null;
        if (persist_first) {
            val = prop.getProperty(key);
            if (val != null) {
                return val;
            }
        }
        if (memCAddrs.containsKey(key)) {
            return memCAddrs.get(key);
        }
        return prop.getProperty(key);
    }

    public Account getDefaultAccount(){
        return defaultAccount;
    }

    public String getContractName() {
        return ContractName;
    }

    public String getContractJarName() {
        return ContractJarName;
    }

    public String getContractPropPath() {
        return contractPropPath;
    }

    public String getDefaultAccountJson() {
        return defaultAccountJson;
    }

    public String getDefaultAccountJsonPwd() {
        return defaultAccountJsonPwd;
    }
}
