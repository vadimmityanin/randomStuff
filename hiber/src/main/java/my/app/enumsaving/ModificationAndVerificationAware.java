package my.app.enumsaving;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class ModificationAndVerificationAware {

    @Column(name = "source_creation_timestamp")
    private Instant sourceCreationTimestamp;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private Instant creationTimestamp;

    @UpdateTimestamp
    @Column(name = "modification_timestamp")
    private Instant modificationTimestamp;

    @Column(name = "verified")
    private boolean verified;

    public ModificationAndVerificationAware() {
    }

    public Instant getSourceCreationTimestamp() {
        return sourceCreationTimestamp;
    }

    public void setSourceCreationTimestamp(Instant sourceCreationTimestamp) {
        this.sourceCreationTimestamp = sourceCreationTimestamp;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getModificationTimestamp() {
        return modificationTimestamp;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}