package my.app.onetomany.bidirectional;

import org.springframework.data.domain.Page;

import java.util.List;

public interface HomeRepository extends QueryDSLRepository<Home,Long>, HomeRepositoryCustom {

    Page<Pr> geOKdto();
    List<Pr> geOKdtoList();
}
