package my.app.manytomany.customrepomasterclass;

import my.app.manytomany.Document;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DocRepCustomImpl extends SimpleJpaRepository<Document, Long> implements DocRepCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public DocRepCustomImpl( EntityManager em) {
        super(Document.class, em);
    }

    @Override
    public Document valera() {
        return entityManager.find(Document.class,5L);
    }
}
