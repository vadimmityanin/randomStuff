package my.app.manytomany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocProj {

    private Long id;

    private String name;

    private String djName;

    private Set<Long> batchIds = new LinkedHashSet<>();

    public DocProj(Long id, String name, String djName) {
        this.id = id;
        this.name = name;
        this.djName = djName;
    }
}
