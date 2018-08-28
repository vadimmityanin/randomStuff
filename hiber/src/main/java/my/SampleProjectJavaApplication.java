package my;

import my.app.Saver;
import my.app.projections.Book;
import my.app.projections.BookRepository;
import my.app.projections.ReportDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SampleProjectJavaApplication extends WebSecurityConfigurerAdapter {

    public static <T> void mmm(T dto) {
        System.err.println(dto.getClass());
    }

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SampleProjectJavaApplication.class, args);
        Saver saver = context.getBean(Saver.class);
        EntityManager em = context.getBean(EntityManager.class);

        saver.jsnob();


    }


}
