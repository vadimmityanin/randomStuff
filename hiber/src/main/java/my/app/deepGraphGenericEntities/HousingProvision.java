//package my.app.deepGraphGenericEntities;
//
//import javax.persistence.*;
//
//import static javax.persistence.FetchType.LAZY;
//
//@Entity
//@Table(name = "housing_provision")
//@AttributeOverride(name = "value", column = @Column(nullable = false))
//public class HousingProvision extends SocialInformationField<Boolean> {
//
//    @MapsId
//    @OneToOne(mappedBy = "socialInformation.housingProvision", fetch = LAZY)
//    @JoinColumn(name = "social_information_id")
//    private SourceProfile socialInformation;
//
//}
