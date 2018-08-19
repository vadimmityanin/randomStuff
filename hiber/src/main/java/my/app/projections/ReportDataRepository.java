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
public class ReportDataRepository extends SimpleJpaRepository<ReportData, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public ReportDataRepository(EntityManager em) {
        super(ReportData.class, em);
    }

    public ReportDTO fillDTO(Long id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportDTO> cbQuery = cb.createQuery(ReportDTO.class);
        Root<ReportData> root = cbQuery.from(ReportData.class);
        cbQuery.multiselect(
                root.get(ReportData_.id),
                root.get(ReportData_.city));
        Predicate equality = cb.equal(root.get(ReportData_.id), id);
        cbQuery.where(equality);
        return entityManager.createQuery(cbQuery).getSingleResult();
    }
}
