package my.app;

import my.app.model.SourceProfile;
import org.springframework.data.repository.CrudRepository;

public interface SourceProfileRepository extends CrudRepository<SourceProfile,Long> {
}
