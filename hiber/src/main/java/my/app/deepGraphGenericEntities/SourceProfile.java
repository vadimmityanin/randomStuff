package my.app.deepGraphGenericEntities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

/*
 * TODO list:
 * 1. All non-null database columns must be backed by Java primitive types if applicable (for instance long id instead of Long id)
 * 2. All OneToOne relationship's ownership should be inverted. Name, Gender, Citizenship... objects should own reference (foreign key in DB) to SourceProfile.
 * Primary key of OneToOne satellite object (Name, Gender, etc.) is a SourceProfile.id which is at the same time foreign key to corresponding SourceProfile
 * */
@Entity
@Table(name = "source_profile")
public class SourceProfile
        extends ModificationAndVerificationAware
{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;


    @Column(name = "auth_id")
    private Long authId;

    // If this field is about user ID in external system it should be String and have more obvious name. Something like 'externalId'
    @Column(name = "external_id")
    private String externalId;


    @Embedded
    private SocialInformation socialInformation;


    public SourceProfile() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }


    public SocialInformation getSocialInformation() {
        return socialInformation;
    }

    public void setSocialInformation(SocialInformation socialInformation) {
        this.socialInformation = socialInformation;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceProfile that = (SourceProfile) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}