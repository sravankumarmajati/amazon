package ac.app.base.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);
    @Autowired
    ContractInitializer contractInitializer;

    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null) {
            try {
                logger.info("                 _       _ _   \n" +
                        "  __ _ _ __  _ __   (_)_ __ (_) |_ \n" +
                        " / _` | '_ \\| '_ \\  | | '_ \\| | __|\n" +
                        "| (_| | |_) | |_) | | | | | | | |_ \n" +
                        " \\__,_| .__/| .__/  |_|_| |_|_|\\__|\n" +
                        "      |_|   |_|                    \n");
                contractInitializer.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
