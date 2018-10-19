package my.app.manytomany.customrepomasterclass;

import my.app.manytomany.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocRep extends CrudRepository<Document, Long>, DocRepCustom {

    Document findByName(String name);

    /**
     *         System.err.println("++++");
     *         DocRep bean = context.getBean(DocRep.class);
     *         System.err.println( bean.findByName("doc1"));
     *         System.err.println( bean.valera());
     *         System.err.println("++++");
     */
}
