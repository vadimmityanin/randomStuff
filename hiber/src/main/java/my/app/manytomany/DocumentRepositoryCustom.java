package my.app.manytomany;

import my.app.onetomany.bidirectional.QueryDSLRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepositoryCustom extends QueryDSLRepository<Document, Long>
//        , CustomQuerydslBinderCustomizer<QDocument>
{

    @Modifying
    @Query(value = "delete from document_batches where documents_id = ?1 AND batches_id = ?2", nativeQuery = true)
   default void removeBatchFromDocument(Long documents_id, Long batches_id){}

    List<Long> selectBatchesForDocument(Long documents_id);

//    @Override
//    default void customize(CustomQuerydslBindings bindings, QDocument document) {
//
//        bindings.bind(document.name).first(Ops.EQ, SimpleExpression::eq);
//
//        bindings.bind(document.name).first(Ops.LIKE, (path, value) -> path.like("%" + value + "%"));
//    }

}
