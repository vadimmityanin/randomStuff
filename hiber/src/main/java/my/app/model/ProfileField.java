package my.app.model;

import javax.persistence.*;

@MappedSuperclass
public class ProfileField<T> extends ModificationAndVerificationAware {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private SourceProfile sourceProfile;


    @Column
    private T value;

    public ProfileField() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SourceProfile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(SourceProfile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

}
