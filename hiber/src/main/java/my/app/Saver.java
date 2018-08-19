package my.app;

import my.app.model.Profile;
import my.app.model.SourceProfile;
import my.app.model.Wealth;
import my.app.onetomany.bidirectional.Floor;
import my.app.onetomany.bidirectional.Home;
import my.app.onetomany.unidirectional.manyside.Eye;
import my.app.onetomany.unidirectional.manyside.Man;
import my.app.onetomany.unidirectional.oneside.Post;
import my.app.onetomany.unidirectional.oneside.Uzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class Saver {
    //    @Autowired
//    private LongProfileFieldRepository longProfileFieldRepository;
    @Autowired
    private SourceProfileRepository sourceProfileRepository;

    @Autowired
    private WealthRepository wealthRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Long go() {

        Profile profile = new Profile();
        profile.setNameOfProfile("profilezz");
        em.persist(profile);

        SourceProfile sourceProfile = new SourceProfile(null, "valera");
        Wealth wealth = new Wealth();

        try {
            wealth.setValue(new URL("http://ukr.net"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        sourceProfile.setProfile(profile);
        SourceProfile sourceProfileSaved = sourceProfileRepository.save(sourceProfile);

//        sourceProfile.setWealth(wealth);
        wealth.setSourceProfile(sourceProfile);
        Wealth savedWealth = wealthRepository.save(wealth);


        em.flush();
//        Long id = sourceProfileSaved.getWealth().getId();
        wealthRepository.deleteById(savedWealth.getId());

        System.err.println("done");
        return savedWealth.getId();

    }

//    @Transactional
//    public void del(Long spID) {
//        wealthRepository.deleteById(spID);
//    }

    @Transactional
    public void puff() {
        SourceProfile sourceProfile = new SourceProfile(null, "valera");
        em.persist(sourceProfile);

        Wealth wealth = new Wealth();
        wealth.setSourceProfile(sourceProfile);
        wealth.setValue(null);
//        Wealth allById = wealthRepository.findAllById(2L);
//        System.err.println(allById.getId());
//        wealthRepository.deleteById(2L);
        wealthRepository.save(wealth);
    }


    @Transactional
    public void oneToManyUnidirOnONESide() {
        Uzer uzer = new Uzer();
        Post post = new Post();
        em.persist(post);
        uzer.getPosts().add(post);
        em.persist(uzer);
        /**
         *     insert
         *     into
         *         post
         *         (id)
         *     values
         *         (?)
         *
         *     insert
         *     into
         *         uzer
         *         (id)
         *     values
         *         (?)
         *
         *     update
         *         post
         *     set
         *         post_id=?
         *     where
         *         id=?
         */
    }

    @Transactional
    public void oneToManyUnidierOnManySide() {
        Eye eye = new Eye();
        Man man = new Man();
        em.persist(man);
        eye.setMan(man);
        em.persist(eye);

    }


    @Transactional
    public long oneToManyBIdirect() {
        Home home = new Home(1L);
        Floor floor = new Floor(11L);
        floor.setHome(home);
        em.persist(floor);
        em.flush();
//        System.err.println(home.getFloors());
        return floor.getId();
    }

    @Transactional
    public void oneToManyBIdirectTEST(long createdFloorId) {
        System.err.println("---");
        System.err.println(em.find(Home.class, 1L).getFloors());

        Floor previouslySavedFloor = em.find(Floor.class, 11L);
        Home newHome = new Home(2L);

        previouslySavedFloor.setHome(null);
        em.persist(previouslySavedFloor);
        em.flush();
        System.err.println(em.find(Home.class, 1L).getFloors());
        System.err.println("---");


    }

    @Transactional
    public void oneToManyBIdirectResults() {

        Home home = em.find(Home.class, 1L);

        System.err.println(home.getFloors());


    }


    @Transactional
    public void oneToManyBIdirectFromONESide() {

        Home home = new Home();
        Floor floor = new Floor();

        home.addFloor(floor);

        em.persist(home);

        System.err.println(home.getFloors());


    }

    @Transactional
    public void oneToManyBIdirectFromONESide2() {

        Home home = em.find(Home.class, 1L);

        home.removeFloor(new Floor(2L));

        em.persist(home);

        System.err.println(home.getFloors());


    }
}
