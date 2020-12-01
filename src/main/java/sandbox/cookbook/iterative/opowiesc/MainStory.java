package sandbox.cookbook.iterative.opowiesc;

import java.util.Date;
import java.util.logging.Logger;


public class MainStory {

    private static Logger logger = Logger.getLogger(MainStory.class.getName());

    public static void main (String[] args) {
        BookStory.story((cool, bad) -> {
            Date inThatDay = new Date();
            Queen.arrived(inThatDay, (money, brought)-> {
                if(Boolean.TRUE.equals(brought)) cool.accept(money);
                else {
                    String warning  = bad.get();
                    logger.info(warning);
                }
            });
        });
    }
}
