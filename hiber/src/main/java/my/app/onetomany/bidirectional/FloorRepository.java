package my.app.onetomany.bidirectional;

import org.springframework.data.domain.Page;

import java.util.List;

public interface FloorRepository extends QueryDSLRepository<Floor,Long>, HomeRepositoryCustom {

    Page<Pr> geOKdto();
    List<Pr> geOKdtoList();
}
