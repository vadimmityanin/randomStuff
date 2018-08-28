package my.app.deepGraphGenericEntities;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Embeddable
public class SocialInformation {

//    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
//    private SocialStatusInfo socialStatusInfo;

//    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
//    private HousingProvision housingProvision;
    //
//    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
//    private Pension pension;
    //
    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "marital_status_id")
    private MaritalStatus maritalStatus;

    public SocialInformation() {
    }


//    public SocialStatusInfo getSocialStatusInfo() {
//        return socialStatusInfo;
//    }
//
//    public void setSocialStatusInfo(SocialStatusInfo socialStatusInfo) {
//        this.socialStatusInfo = socialStatusInfo;
//    }
//
//    public HousingProvision getHousingProvision() {
//        return housingProvision;
//    }
//
//    public void setHousingProvision(HousingProvision housingProvision) {
//        this.housingProvision = housingProvision;
//    }
//
//    public Pension getPension() {
//        return pension;
//    }
//
//    public void setPension(Pension pension) {
//        this.pension = pension;
//    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}