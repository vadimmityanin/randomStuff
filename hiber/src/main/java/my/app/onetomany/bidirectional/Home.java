package my.app.onetomany.bidirectional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Home {

    @Id
    @GeneratedValue
    private long id;

//    @OneToMany(mappedBy = "home",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private Set<Floor> floors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private UnitDepartment department;

    @Column
    private UUID code;

    @Column
    private String name;


//    public void addFloor(Floor floor){
//        Objects.requireNonNull(floor,"Floor can't be null.");
//        floor.setHome(this);
//        floors.add(floor);
//    }
//
//    public void removeFloor(Floor floor){
//        Objects.requireNonNull(floor,"Floor can't be null.");
//        floor.setHome(null);
//        floors.remove(floor);
//    }




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
