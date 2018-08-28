//package my.app.model;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.ZonedDateTime;
//
//@MappedSuperclass
//public class ModificationAndVerificationAware {
//
//    @Column(name = "source_creation_timestamp")
//    private ZonedDateTime sourceCreationTimestamp;
//
//    @Column(name = "creation_timestamp")
//    private ZonedDateTime creationTimestamp;
//
//    @Column(name = "modification_timestamp")
//    private ZonedDateTime modificationTimestamp;
//
//    @Column(name = "verified")
//    private boolean verified;
//
//    public ModificationAndVerificationAware() {
//    }
//
//    public ZonedDateTime getSourceCreationTimestamp() {
//        return sourceCreationTimestamp;
//    }
//
//    public ZonedDateTime getCreationTimestamp() {
//        return creationTimestamp;
//    }
//
//    public ZonedDateTime getModificationTimestamp() {
//        return modificationTimestamp;
//    }
//
//    public boolean getVerified() {
//        return verified;
//    }
//
//    public void setSourceCreationTimestamp(ZonedDateTime sourceCreationTimestamp) {
//        this.sourceCreationTimestamp = sourceCreationTimestamp;
//    }
//
//    public void setCreationTimestamp(ZonedDateTime creationTimestamp) {
//        this.creationTimestamp = creationTimestamp;
//    }
//
//    public void setModificationTimestamp(ZonedDateTime modificationTimestamp) {
//        this.modificationTimestamp = modificationTimestamp;
//    }
//
//    public void setVerified(boolean verified) {
//        this.verified = verified;
//    }
//}
