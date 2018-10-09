package my.app.manytomany;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToMany
    private Set<Document> documents;

    @ManyToOne(fetch = FetchType.LAZY)
    private Batch batch;
}
