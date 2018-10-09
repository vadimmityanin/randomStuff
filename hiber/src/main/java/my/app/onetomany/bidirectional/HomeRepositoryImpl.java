package my.app.onetomany.bidirectional;

import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;

public class HomeRepositoryImpl extends BaseRepository<Home, Long> implements HomeRepository {


    public HomeRepositoryImpl(EntityManager entityManager) {
        super(Home.class, entityManager);
    }

    @Override
    public Home getIDZ(Long id) {

        QHome home = QHome.home;
        return new JPAQuery<Home>(entityManager)
                .from(home)
//                .innerJoin(home.floors).fetchJoin()
                .where(home.id.eq(id)).fetch().get(0);
    }
}
