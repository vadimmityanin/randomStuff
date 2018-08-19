package my.app.onetomany.unidirectional.manyside;

import javax.persistence.*;

@Entity
public class Eye {

    //u cannot just set new Man and try to save Eye,
    //u must save man first! (it is one-way relationship)

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "man_id")
    private Man man;

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
