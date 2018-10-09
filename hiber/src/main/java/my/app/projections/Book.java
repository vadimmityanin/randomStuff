package my.app.projections;

import my.app.deepGraphGenericEntities.ModificationAndVerificationAware;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Book extends ModificationAndVerificationAware {

    @Id
    private Long id;
    @Column
    private String city;
    @Column
    private long seats;
    @Column
    private String name;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("city='" + city + "'")
                .add("seats=" + seats)
                .add("name='" + name + "'")
                .toString();
    }
}
