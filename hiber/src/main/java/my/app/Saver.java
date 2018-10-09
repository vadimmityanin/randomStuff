package my.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.app.deepGraphGenericEntities.MaritalStatus;
import my.app.deepGraphGenericEntities.SocialInformation;
import my.app.deepGraphGenericEntities.SourceProfile;
import my.app.enumsaving.Enz;
import my.app.enumsaving.OOO;
import my.app.idsswap.Arr;
import my.app.idsswap.ArrRepository;
import my.app.jsonb.DTOz;
import my.app.jsonb.Docz;
import my.app.jsonb.Signed;
import my.app.manytomany.Batch;
import my.app.manytomany.Document;
import my.app.manytomany.Item;
import my.app.manytomany.Transaction;
import my.app.onetomany.bidirectional.Floor;
import my.app.onetomany.bidirectional.Home;
import my.app.onetomany.unidirectional.manyside.Eye;
import my.app.onetomany.unidirectional.manyside.Man;
import my.app.onetomany.unidirectional.oneside.Post;
import my.app.onetomany.unidirectional.oneside.Uzer;
import my.app.projections.*;
import my.app.urldeserial.URLka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URL;

@Component
public class Saver {
    //    @Autowired
//    private LongProfileFieldRepository longProfileFieldRepository;
//    @Autowired
//    private SourceProfileRepository sourceProfileRepository;

    @Autowired
    private ArrRepository arrRepository;

    @Autowired
    private ObjectMapper om;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
//    @Transactional
//    public Long go() {
//
//        Profile profile = new Profile();
//        profile.setNameOfProfile("profilezz");
//        em.persist(profile);
//
//        SourceProfile sourceProfile = new SourceProfile(null, "valera");
//        Wealth wealth = new Wealth();
//
//        try {
//            wealth.setValue(new URL("http://ukr.net"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        sourceProfile.setProfile(profile);
//        SourceProfile sourceProfileSaved = sourceProfileRepository.save(sourceProfile);
//
////        sourceProfile.setWealth(wealth);
//        wealth.setSourceProfile(sourceProfile);
//        Wealth savedWealth = wealthRepository.save(wealth);
//
//
//        em.flush();
////        Long id = sourceProfileSaved.getWealth().getId();
//        wealthRepository.deleteById(savedWealth.getId());
//
//        System.err.println("done");
//        return savedWealth.getId();
//
//    }
//
////    @Transactional
////    public void del(Long spID) {
////        wealthRepository.deleteById(spID);
////    }
//
//    @Transactional
//    public void puff() {
//        SourceProfile sourceProfile = new SourceProfile(null, "valera");
//        em.persist(sourceProfile);
//
//        Wealth wealth = new Wealth();
//        wealth.setSourceProfile(sourceProfile);
//        wealth.setValue(null);
////        Wealth allById = wealthRepository.findAllById(2L);
////        System.err.println(allById.getId());
////        wealthRepository.deleteById(2L);
//        wealthRepository.save(wealth);
//    }


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
    public void oneToManyBIdirect() {
        Home home = new Home();
        Floor floor = new Floor();
        Floor floor2 = new Floor();
        Floor floor3 = new Floor();
        em.persist(floor);
        em.persist(floor2);
        em.persist(floor3);
        System.err.println("------------floors saved");
//        floor.setHome(home);
//        floor2.setHome(home);
        em.persist(home);
        em.flush();
//        System.err.println(home.getFloors());
        System.err.println("---done---1");

    }
    @Transactional
    public void oneToManyBIdirect2() {
        Floor floor1 = em.getReference(Floor.class, 1L);
        Floor floor2 = em.getReference(Floor.class, 2L);
        Floor floor3 = em.getReference(Floor.class, 3L);
        Home home = em.find(Home.class, 4L);
home.addFloor(floor1);
home.addFloor(floor2);
home.addFloor(floor3);
        System.err.println("---done2---");
    }
    @Transactional
    public void oneToManyBIdirect3() {
        Home home = em.find(Home.class, 4L);
        System.err.println(home.getFloors());
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

    @Transactional
    public void checkEmb() {
        SourceProfile sourceProfile = new SourceProfile();
        SocialInformation socialInformation = new SocialInformation();
        em.persist(sourceProfile);
        em.flush();

        MaritalStatus maritalStatus = new MaritalStatus();
        maritalStatus.setValue("SIXTYSIX");
        socialInformation.setMaritalStatus(maritalStatus);
        sourceProfile.setSocialInformation(socialInformation);
        maritalStatus.setSourceProfile(sourceProfile);
        em.persist(sourceProfile);
    }

    @Transactional
    public void checkEmb2() {
        Book book = new Book();
        book.setId(5L);
        book.setCity("her");
        book.setName("na");
        book.setSeats(5_000);
        em.persist(book);
    }

    @Transactional
    public void jsnob() {
        Signed signed = new Signed();
        signed.setName("vadim");
        signed.setSurname("mitya");

        DTOz dtOz = new DTOz();
        dtOz.setLala("l");
        dtOz.setFa("ffff");
        dtOz.setName("kolya");

        Docz docz = new Docz();
        docz.setJsonchik(dtOz);
        docz.setId(5L);
        em.persist(docz);
    }

    @Transactional
    public void enumzFail() {
        Enz enz = new Enz();
        OOO ooo = new OOO();
        enz.setOoo(ooo);

        em.persist(enz);
        //attempted to assign id from null one-to-one property [my.app.enumsaving.OOO.enz]
    }

    @Transactional
    public void enumz() {
        Enz enz = new Enz();
        OOO ooo = new OOO();
        ooo.setEnz(enz);
        enz.setOoo(ooo);

        em.persist(enz);
        //attempted to assign id from null one-to-one property [my.app.enumsaving.OOO.enz]
    }

    @Transactional
    public void enumz2() {


        OOO ooo = em.find(OOO.class, 1L);
        em.remove(ooo);
        //attempted to assign id from null one-to-one property [my.app.enumsaving.OOO.enz]
    }

    @Transactional
    public void idswap() {
        Arr herz = arrRepository.save(new Arr("herz"));
        System.err.println(herz.getId());
        System.err.println("AAAAAAAAAAAAAA");
        Arr vasya = new Arr("vasya");
        vasya.setId(herz.getId());
        arrRepository.save(vasya);

    }

    @Transactional
    public void urlka() throws Exception {
        URLka urLka = new URLka();
        urLka.setUrl(new URL("http://ukr.net"));
        em.persist(urLka);
        String s = om.writeValueAsString(urLka);
        System.err.println(s);
        URLka urLka1 = om.readValue(s, URLka.class);

    }

    @Transactional
    public void manyToManyProjections() throws Exception {

        PageRequest of = PageRequest.of(1, 1, Sort.Direction.ASC, "id");
//        Page<Author> authorsz = authorRepository.getAuthorsz(of, (root, cq, cb) -> cb.isNotNull(root.get(Author_.id)));
//        List<List<Book>> collect = authorsz.stream().map(a -> a.getBooks()).collect(Collectors.toList());
//        System.err.println(collect);
        Page<AuthorProjection> authorProjectionzz = authorRepository.getAuthorProjectionzz(of,
//                (root, cq, cb) -> cb.equal(root.get(Author_.id), 2L));
                (root, cq, cb) -> {
////                    final Join<Author, Book> addresses = root.join(Author_.books, JoinType.LEFT);
                    return cb.and(
                            cb.isNotNull(root.get(Author_.id)));
                });
//        System.err.println(authorsz.getTotalElements());
//        System.err.println(authorsz.getTotalPages());
//        System.err.println(authorsz.getTotalPages());
        System.err.println("---");
        Author author = new Author();
        author.setId(3L);
        Book reference = em.getReference(Book.class, 33L);
        author.addBook(reference);
        em.persist(author);

        System.err.println(bookRepository.fillDTO(22L));
        System.err.println(bookRepository.findAllByCity(null));

    }

    @Transactional
    public Author prep() {
        Author author = new Author();
        author.setId(1L);

        Author author2 = new Author();
        author2.setId(2L);

        Book book = new Book();
        book.setId(11L);

//        em.persist(book);

        Book book2 = new Book();
        book2.setId(22L);
//        em.persist(book2);

        Book book3 = new Book();
        book3.setId(33L);
//        em.persist(book3);

        author.addBook(book);
        author.addBook(book2);
        author.addBook(book3);

        author2.addBook(book);

        em.persist(author);
        em.persist(author2);
        return author;
    }

    @Transactional
    public void batches() throws Exception {
        Batch batch1 = Batch.builder().name("batch1").build();
        Batch batch2 = Batch.builder().name("batch2").parent(batch1).build();

        Item item1 = Item.builder().name("item1").batch(batch1).build();
        Item item2 = Item.builder().name("item2").batch(batch2).build();

        Document doc1 = Document.builder().name("doc1").build();
        doc1.getBatches().add(batch1);
        doc1.getBatches().add(batch2);
        doc1.getItems().add(item1);
        doc1.getItems().add(item2);

        Transaction tx1 = Transaction.builder().name("tx1").build();
        tx1.setDocument(doc1);

        em.persist(batch1);
        em.persist(batch2);
        em.persist(item1);
        em.persist(item2);
        em.persist(doc1);
        em.persist(tx1);


    }
}