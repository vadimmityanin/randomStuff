package my.app.deepGraphGenericEntities;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@MappedSuperclass
public class SocialInformationField<T> extends ModificationAndVerificationAware{

    @Id
    private long id;
    @MapsId
    @OneToOne(mappedBy = "socialInformation.maritalStatus", fetch = LAZY)
    @JoinColumn(name = "source_profile_id")
    private SourceProfile sourceProfile;



    @Column(name = "value")
    private T value;

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

    public SourceProfile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(SourceProfile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }
}
