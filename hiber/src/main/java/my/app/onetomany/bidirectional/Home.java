package my.app.onetomany.bidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Home {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "home",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Floor> floors = new HashSet<>();

    public Home(long id) {
        this.id = id;
    }

    public Home() {
    }

    public void addFloor(Floor floor){
        Objects.requireNonNull(floor,"Floor can't be null.");
        floor.setHome(this);
        floors.add(floor);
    }

    public void removeFloor(Floor floor){
        Objects.requireNonNull(floor,"Floor can't be null.");
        floor.setHome(null);
        floors.remove(floor);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Floor> getFloors() {
        return floors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return id == home.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Home.class.getSimpleName() + "[", "]")
                .add("id=" + id)
//                .add("floors=" + floors)
                .toString();
    }
}
