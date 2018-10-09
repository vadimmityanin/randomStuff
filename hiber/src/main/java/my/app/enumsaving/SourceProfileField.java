package my.app.enumsaving;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SourceProfileField<T> extends ModificationAndVerificationAware {

    @Id
    @GeneratedValue
    private long id;
//    @MapsId
//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "id")
//    private SourceProfile sourceProfile;

    @Column(name = "value")
    private T value;

    public SourceProfileField() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public SourceProfile getSourceProfile() {
//        return sourceProfile;
//    }

//    public void setSourceProfile(SourceProfile sourceProfile) {
//        this.sourceProfile = sourceProfile;
//    }
}