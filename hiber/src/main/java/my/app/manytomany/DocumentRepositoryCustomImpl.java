package my.app.manytomany;

import my.app.onetomany.bidirectional.BaseRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepositoryCustomImpl extends BaseRepository<Document, Long> implements DocumentRepositoryCustom {

    private final EntityManager em;

    public DocumentRepositoryCustomImpl(EntityManager entityManager) {
        super(Document.class, entityManager);
        em = entityManager;
    }

    public List<Long> selectBatchesForDocument(Long documents_id) {
        javax.persistence.Query nativeQuery = em.createNativeQuery("select batches_id from document_batches where documents_id = :documents_id");
        nativeQuery.setParameter("documents_id", documents_id);
        List<BigInteger> resultList = nativeQuery.getResultList();

        return resultList.stream().mapToLong(BigInteger::longValue).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
