package ac.app;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ServletComponentScan
public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    static {
        try {
            //初始化log4j
            String log4jPath = MainApplication.class.getClassLoader().getResource("").getPath() + "log4j"
                    + ".properties";
            logger.info("初始化Log4j...");
            logger.info("path is " + log4jPath);
            PropertyConfigurator.configure(log4jPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

    }
}
