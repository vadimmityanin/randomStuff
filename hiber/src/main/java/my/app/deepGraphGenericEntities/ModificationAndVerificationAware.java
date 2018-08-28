package my.app.deepGraphGenericEntities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class ModificationAndVerificationAware {


    @Column(name = "source_creation_timestamp")
    private Timestamp sourceCreationTimestamp;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private Timestamp creationTimestamp;
    @UpdateTimestamp
    @Column(name = "modification_timestamp")
    private Timestamp modificationTimestamp;

    @Column(name = "verified")
    private boolean verified;

    public ModificationAndVerificationAware() {
    }

    public Timestamp getSourceCreationTimestamp() {
        return sourceCreationTimestamp;
    }

    public void setSourceCreationTimestamp(Timestamp sourceCreationTimestamp) {
        this.sourceCreationTimestamp = sourceCreationTimestamp;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Timestamp getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(Timestamp modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
