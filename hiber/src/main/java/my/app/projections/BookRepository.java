package my.app.projections;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class BookRepository extends SimpleJpaRepository<Book, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public BookRepository(EntityManager em) {
        super(Book.class, em);
    }

    public ReportDTO fillDTO(Long id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportDTO> cbQuery = cb.createQuery(ReportDTO.class);
        Root<Book> root = cbQuery.from(Book.class);
        cbQuery.multiselect(
                root.get(Book_.id),
                root.get(Book_.city));
        Predicate equality = cb.equal(root.get(Book_.id), id);
        cbQuery.where(equality);
        return entityManager.createQuery(cbQuery).getSingleResult();
    }
}
