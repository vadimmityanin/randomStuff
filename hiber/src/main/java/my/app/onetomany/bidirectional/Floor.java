package my.app.onetomany.bidirectional;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Floor {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    public Floor(long id) {
        this.id = id;
    }

    public Floor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;

    }


//    public void setHomerrrrr(Home home) {
//        Objects.requireNonNull(home, "Home can not be null");
//        this.home = home;
//        home.addFloor(this);
//    }
//
//    public void removeHome() {
//        ofNullable(this.home).ifPresent(currentHome -> currentHome.removeFloor(this));
//        this.home = null;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id == floor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", home_id=" + home.getId() +
                '}';
    }
}
