package my.app.enumsaving;

import javax.persistence.*;

@Entity
public class Enz {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @OneToOne(mappedBy ="enz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private OOO ooo;

    public OOO getOoo() {
        return ooo;
    }

    public void setOoo(OOO ooo) {
        this.ooo = ooo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }
}
