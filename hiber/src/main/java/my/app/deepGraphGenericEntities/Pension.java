//package my.app.deepGraphGenericEntities;
//
//import javax.persistence.*;
//
//import static javax.persistence.FetchType.LAZY;
//
//@Entity
//@Table(name = "pension_condition")
//public class Pension extends SocialInformationField<String> {
//
//    @MapsId
//    @OneToOne(mappedBy = "socialInformation.socialStatusInfo", fetch = LAZY)
//    @JoinColumn(name = "social_information_id")
//    private SourceProfile socialInformation;
//
//}
