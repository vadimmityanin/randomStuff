//package my.app.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//@Entity
//public class Profile {
//    @Id
//    @GeneratedValue
//    private long id;
//
//    @Column
//    private String nameOfProfile;
//
////    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
////    private List<SourceProfile> sourceProfilesList = new ArrayList<>();
//
//    public Profile() {
//    }
//
//    public String getNameOfProfile() {
//        return nameOfProfile;
//    }
//
//    public void setNameOfProfile(String nameOfProfile) {
//        this.nameOfProfile = nameOfProfile;
//    }
//
////    public List<SourceProfile> getSourceProfilesList() {
////        return sourceProfilesList;
////    }
////
////    public void setSourceProfilesList(List<SourceProfile> sourceProfilesList) {
////        this.sourceProfilesList = sourceProfilesList;
////    }
////
////    public void addSourceProfile(SourceProfile sourceProfile){
////        sourceProfilesList.add(sourceProfile);
////        sourceProfile.setProfile(this);
////    }
////
////    public void removeSourceProfile(SourceProfile sourceProfile){
////        sourceProfilesList.remove(sourceProfile);
////        sourceProfile.setProfile(null);
////    }
//}
