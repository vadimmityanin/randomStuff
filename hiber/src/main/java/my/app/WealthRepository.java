package my.app;

import my.app.model.Wealth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WealthRepository extends CrudRepository<Wealth,Long> {
    void deleteById(Long id);
    Wealth findAllById(Long id);
}
