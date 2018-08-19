package my.app.model;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "source_profile")
public class SourceProfile {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "sourceProfile", fetch = LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
//    @AttributeOverride(name = "value", column = @Column(name = "value", table = "anger"))
//    @Column(table = "love")
    private Wealth wealth;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "in_source_profile_join_column_for_profile")
    private Profile profile;


    public SourceProfile() {
    }

    public SourceProfile(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
//    public void setProfile(Profile profile) {
//        if (profile == null) {
//            ofNullable(this.profile)
//                    .ifPresent(currentProfile -> currentProfile.removeSourceProfile(this));
//        } else {
//            profile.addSourceProfile(this);
//        }
//        this.profile = profile;
//    }

    public Wealth getWealth() {
        return wealth;
    }

    public void setWealth(Wealth wealth) {
        this.wealth = wealth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
