package my.app.onetomany.bidirectional;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.app.manytomany.DocProj;
import my.app.manytomany.Document;
import my.app.manytomany.DocumentRepository;
import my.app.manytomany.QDocument;
import my.app.manytomany.binding.CustomQuerydslPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@RestController
public class Ctrlz {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/docs")
    public Page<DocProj> got(@CustomQuerydslPredicate(root = Document.class) Predicate predicate,
                             Pageable pageable
//                             ,@RequestParam( name ="_any-field-match_", required = false) String anyFieldSearchValue
    ) {

//        List<Long> dto = documentRepository.findDTO(null, LONG_PROJ);
//
//        System.err.println(dto);
//                .leftJoin(QDocument.document.dj)
//                .innerJoin(QDocument.document.batches)
//                .where(predicate);


        JPAQuery<DocProj> query = new JPAQuery<DocProj>().from(QDocument.document)
                .leftJoin(QDocument.document.dj)
//                .innerJoin(QDocument.document.batches)
                .where(predicate);

        Page<DocProj> all = documentRepository.findDTOsPage(query, pageable,
                Projections.constructor(DocProj.class,
                        QDocument.document.id,
                        QDocument.document.name,
                        QDocument.document.dj.name
                ));
        all.map((p -> {
            List<Long> longs = documentRepository.selectBatchesForDocument(p.getId());
            p.getBatchIds().addAll(longs);
            return p;
        }));

        return all;
    }

    @PostMapping
    public void gotInc(@RequestBody Valera valera) {
        System.err.println(valera);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Valera {
        UUID name;

        final Map<String, String> map = new HashMap<>();

        @Override
        public String toString() {
            return new StringJoiner(", ", Valera.class.getSimpleName() + "[", "]")
                    .add("name=" + name)
                    .add("map=" + map)
                    .toString();
        }
    }

}
