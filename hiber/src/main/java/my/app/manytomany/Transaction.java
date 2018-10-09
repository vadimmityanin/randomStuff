package my.app.manytomany;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Transaction {

    @Id
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Document document;
}
