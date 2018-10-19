package my.app.onetomany.bidirectional;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public Iterable<Home> go() {
        BooleanExpression eq = QHome.home.id.eq(4L);
        return homeRepository.findAll(eq,PageRequest.of(0,1));
    }
    public Page<Pr> go2() {
        QHome home = QHome.home;
        BooleanExpression eq = QHome.home.id.eq(4L);
        return homeRepository.findDTOsPage(eq, PageRequest.of(0,4), Projections.constructor(Pr.class, home.id));
    }
}
