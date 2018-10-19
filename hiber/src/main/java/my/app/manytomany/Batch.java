package my.app.manytomany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "batches")
    private final Set<Document> documents = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Batch parent;

    public void addDocument(Document document) {
        documents.add(document);
        document.getBatches().add(this);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
        document.getBatches().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batch)) return false;
        return id != null && id.equals(((Batch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Batch.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("documents=" + documents)
                .toString();
    }
}
