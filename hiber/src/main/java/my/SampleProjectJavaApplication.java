package my;

import my.app.Saver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SampleProjectJavaApplication {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SampleProjectJavaApplication.class, args);
        Saver saver = context.getBean(Saver.class);
        EntityManager em = context.getBean(EntityManager.class);

////        saver.prep();
////        saver.manyToManyProjections();
//        saver.oneToManyBIdirect();
////        saver.oneToManyBIdirect2();
////        saver.oneToManyBIdirect3();
//
//        HomeService repo = context.getBean(HomeService.class);
//        System.err.println("illgoing=-----");
//
//        FloorRepository ff = context.getBean(FloorRepositoryImpl.class);
//        HomeRepository hh = context.getBean(HomeRepositoryImpl.class);
////        Home ef12 = rr.getByCode(UUID.fromString("b9a20376-cc5f-11e8-a8d5-f2801f1b9fd1"));
////        List<Pr> dto = ((HomeRepositoryImpl) rr).findDTO(
////                HOME.code.eq(UUID.fromString("b9a20376-cc5f-11e8-a8d5-f2801f1b9fd1")),
////
////                Projections.constructor(Pr.class, HOME.code, HOME.department.unit.id));
////        Page<Pr> dtOs = ((HomeRepositoryImpl) rr).geOKdto();
////        HOME.department.unit.id
////        System.err.println(dtOs);
//
////        Page<Pr> prs = hh.geOKdto();
//        Page<Pr> prs = ff.geOKdto();
//        System.err.println(prs);
  saver.batches();
  saver.batches2();
  saver.batches3();


    }


}
