package my.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTTRepository extends CrudRepository<TTTZ,Long> {
}
