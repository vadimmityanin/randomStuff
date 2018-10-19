package my.app.onetomany.bidirectional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static my.app.onetomany.bidirectional.DSLQueryTypes.FLOOR;
import static my.app.onetomany.bidirectional.DSLQueryTypes.HOME;

//import com.querydsl.jpa.JPASubQuery;

public class FloorRepositoryImpl extends BaseRepository<Floor, Long> implements FloorRepository {


    public FloorRepositoryImpl(EntityManager entityManager) {
        super(Floor.class, entityManager);
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
        return new JPAQuery<Home>(entityManager).from(HOME).where(HOME.code.eq(code)).fetch().get(0);
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

//        query.transform(
//                GroupBy.groupBy(employee.department)
//                        .as(GroupBy.list(Projections.tuple(employee.id, employee.firstName, employee.lastName))))

//        return findDTOsPage(new JPAQuery<>(entityManager)
//                .from(FLOOR)
//                .innerJoin(FLOOR.home)
//                .where(QFloor.floor.name.eq("aska"))
//                        .transform(
//                                GroupBy.groupBy(QFloor.floor.home.id, QFloor.floor.home.name)
//                                .as(
//                                Projections.constructor(Pr.class, QFloor.floor.home.id, QFloor.floor.home.name)))
//
//                ,PageRequest.of(0,5));

//        JPAQuery<Tuple> aska = new JPAQuery<>(entityManager)
//                .select(QFloor.floor.home.id, QFloor.floor.home.name)
//                .from(FLOOR)
//                .innerJoin(FLOOR.home)
//                .where(QFloor.floor.name.eq("aska"))
//                .groupBy(QFloor.floor.home.id, QFloor.floor.home.name);
////                .groupBy(QFloor.floor.home.id, QFloor.floor.home.name);
//
//        JPQLQuery<Pr> subka = JPAExpressions.select(Projections.constructor(Pr.class, QFloor.floor.home.id, QFloor.floor.home.name))
//                .from(FLOOR)
//                .innerJoin(FLOOR.home)
//                .where(QFloor.floor.name.eq("aska"))
//                .groupBy(QFloor.floor.home.id, QFloor.floor.home.name);


//        System.err.println(aska.fetchCount());
//        return null;
            return    findDTOsPage(
                    FLOOR.home.id.eq(1L)
        ,PageRequest.of(0,5)
                    ,Projections.constructor(Pr.class, FLOOR.id,FLOOR.home.name));

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

//        return findDTO(
//                FLOOR.name.eq("aska")
//                ,Projections.constructor(Pr.class, QFloor.floor.home.id, QFloor.floor.home.name));
        return new JPAQuery<>(entityManager)
                .select(Projections.constructor(Pr.class, QFloor.floor.home.id, QFloor.floor.home.name))
                .from(FLOOR)
                .innerJoin(FLOOR.home)
                .groupBy(QFloor.floor.home.id, QFloor.floor.home.name)
                .where(QFloor.floor.name.eq("aska"))
                .fetch();


    }
}
