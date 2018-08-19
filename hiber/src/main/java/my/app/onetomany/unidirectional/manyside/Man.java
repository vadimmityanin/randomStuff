package my.app.onetomany.unidirectional.manyside;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Man {

    @Id
    @GeneratedValue
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
