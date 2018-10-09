package my.app.manytomany;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private final Set<Batch> batches = new HashSet<>();

    @ManyToMany
    private final Set<Item> items = new HashSet<>();

    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Transaction transaction;
}
