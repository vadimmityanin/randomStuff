package my.app.manytomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private UUID code;

    private LocalDate date;

    @ManyToOne
    private Dj dj;

    @ManyToMany
//            (mappedBy = "documents")
    @JsonIgnore
    private final Set<Batch> batches = new HashSet<>();

    @ManyToMany(mappedBy = "documents")
    @JsonIgnore
    private final Set<Item> items = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Document.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
//                .add("items=" + items)
                .toString();
    }
}
