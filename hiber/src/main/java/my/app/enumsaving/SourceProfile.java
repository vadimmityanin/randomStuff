//package my.app.enumsaving;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//import static java.util.Optional.ofNullable;
//import static javax.persistence.CascadeType.*;
//import static javax.persistence.FetchType.LAZY;
//
//@Entity
//@Table(name = "enum_source_profile")
//public class SourceProfile {
//
//    @Id
//    private long id;
//
//    @OneToOne(mappedBy = "sourceProfile", cascade = ALL, fetch = LAZY)
//    private Gender gender;
//
//
//    @OneToOne(mappedBy = "sourceProfile", cascade = ALL, fetch = LAZY)
//    private Nationality nationality;
//
//
//    public SourceProfile() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        if (gender != null) {
//            gender.setSourceProfile(this);
//        }
//        this.gender = gender;
//    }
//
//    public Nationality getNationality() {
//        return nationality;
//    }
//
//    public void setNationality(Nationality nationality) {
//        if (nationality != null) {
//            nationality.setSourceProfile(this);
//        }
//        this.nationality = nationality;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SourceProfile that = (SourceProfile) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}