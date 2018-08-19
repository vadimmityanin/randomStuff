package my.app;

import javax.persistence.*;

@Entity
@Table(name = "tttz")
public class TTTZ {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aaa")
    @SequenceGenerator(name = "aaa", sequenceName = "ttt3", allocationSize = 8)
    private Long id;

    @Column(name = "name")
    private String name;

    public TTTZ() {
    }

    public TTTZ(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
