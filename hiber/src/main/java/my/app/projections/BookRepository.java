package my.app.projections;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom {

    List<Book> findAllByCity(String city);
    List<String> findNameById(Long id);
}
