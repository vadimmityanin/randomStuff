package my.app.enumsaving;

import javax.persistence.*;

@Entity
public class OOO {
    @Id
    private long id;

    @MapsId
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Enz enz;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enz getEnz() {
        return enz;
    }

    public void setEnz(Enz enz) {
        this.enz = enz;
    }
}
