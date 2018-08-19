package my;

import my.app.Saver;
import my.app.projections.ReportDTO;
import my.app.projections.ReportData;
import my.app.projections.ReportDataRepository;
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

//        Saver saver = context.getBean(Saver.class);
//        Long go = saver.go();
//        saver.del(go);
//        saver.puff();
//        EntityManager em = context.getBean(EntityManager.class);

//        long l = saver.oneToManyBIdirect();
//        saver.oneToManyBIdirectTEST(l);
//        saver.oneToManyBIdirectResults();

//        saver.oneToManyBIdirectFromONESide();
//        saver.oneToManyBIdirectFromONESide2();

        ReportDataRepository reportDataRepository = context.getBean(ReportDataRepository.class);

        ReportData reportData = new ReportData();
        reportData.setId(5L);
        reportData.setCity("Kiev");
        reportData.setName("Olimp");
        reportData.setSeats(70_000);

        reportDataRepository.save(reportData);

        ReportDTO reportDTO = reportDataRepository.fillDTO(reportData.getId());
        System.err.println(reportDTO);
    }

}
