package my.app.onetomany.bidirectional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static my.app.onetomany.bidirectional.DSLQueryTypes.FLOOR;
import static my.app.onetomany.bidirectional.DSLQueryTypes.HOME;

public class HomeRepositoryImpl extends BaseRepository<Home, Long> implements HomeRepository {


    public HomeRepositoryImpl(EntityManager entityManager) {
        super(Home.class, entityManager);
    }

    @Override
    public Home getIDZ(Long id) {


        return new JPAQuery<Home>(entityManager)
                .from(HOME)
//                .innerJoin(home.floors).fetchJoin()
                .where(HOME.id.eq(id)).fetch().get(0);
    }

    @Override
    public Home getByCode(UUID code) {
        JPAQuery<Home> homeJPAQuery = new JPAQuery<>(entityManager);
//        homeJPAQuery.
        return homeJPAQuery.from(HOME).where(HOME.code.eq(code)).fetch().get(0);
    }

    @Override
    public Page<Pr> geOKdto() {
//        return findDTOsPage(new JPAQuery<Home>(entityManager)
//                        .select(Projections.constructor(Pr.class, QFloor.floor.home.code, QFloor.floor.home.id))
//                        .from(QFloor.floor)
//                        .leftJoin(QFloor.floor.home).fetchJoin()
//                .where(QFloor.floor.name.eq("aska"))
//                .groupBy(QFloor.floor.home.code, QFloor.floor.home.id),
//                PageRequest.of(0, 7)
//        );

        JPQLQuery<Long> codision = JPAExpressions.select(FLOOR.home.id)
                .from(FLOOR).where(FLOOR.name.eq("aska"));
//        List<Long> fetch = new JPAQuery<>(entityManager).select(HOME.id).from(HOME)
//                .where(HOME.id.in(codision)).fetch();
//        System.err.println(fetch);
        return findDTOsPage(
                HOME.id.in(codision)
                , PageRequest.of(0, 7)
                , Projections.constructor(Pr.class, HOME.id, HOME.name));

//    return null;
    }

    @Override
    public List<Pr> geOKdtoList() {
//        return findDTOsPage(new JPAQuery<Home>(entityManager)
//                        .select(Projections.constructor(Pr.class, QFloor.floor.home.code, QFloor.floor.home.id))
//                        .from(QFloor.floor)
//                        .leftJoin(QFloor.floor.home).fetchJoin()
//                .where(QFloor.floor.name.eq("aska"))
//                .groupBy(QFloor.floor.home.code, QFloor.floor.home.id),
//                PageRequest.of(0, 7)
//        );

        return findDTO(
                FLOOR.name.eq("aska")
                , Projections.constructor(Pr.class, FLOOR.id, FLOOR.id));

    }
}
