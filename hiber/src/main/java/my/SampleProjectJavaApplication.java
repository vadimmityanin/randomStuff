package my;

import my.app.Saver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SampleProjectJavaApplication extends WebSecurityConfigurerAdapter {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SampleProjectJavaApplication.class, args);
//        Populator populator = context.getBean(Populator.class);
//        populator.go();

        Saver saver = context.getBean(Saver.class);
//        Long go = saver.go();
//        saver.del(go);
//        saver.puff();
//        EntityManager em = context.getBean(EntityManager.class);

//        long l = saver.oneToManyBIdirect();
//        saver.oneToManyBIdirectTEST(l);
//        saver.oneToManyBIdirectResults();

        saver.oneToManyBIdirectFromONESide();
        saver.oneToManyBIdirectFromONESide2();
    }

}
