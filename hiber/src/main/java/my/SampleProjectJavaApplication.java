package my;

import my.app.Saver;
import my.app.onetomany.bidirectional.HomeService;
import my.app.onetomany.bidirectional.Pr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SampleProjectJavaApplication {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SampleProjectJavaApplication.class, args);
        Saver saver = context.getBean(Saver.class);
        EntityManager em = context.getBean(EntityManager.class);

//        System.err.println(context.getBean(CtrlAdvice.class));
//        saver.prep();
//        saver.manyToManyProjections();
        saver.oneToManyBIdirect();
        saver.oneToManyBIdirect2();
        saver.oneToManyBIdirect3();

        System.err.println("MMMMMM");
        HomeService repo = context.getBean(HomeService.class);
        System.err.println("illgoing=-----");
        Page<Pr> prs = repo.go2();
        System.err.println(prs.getContent());
    }


}
