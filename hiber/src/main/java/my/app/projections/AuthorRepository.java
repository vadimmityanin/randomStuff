package my.app.projections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

@Repository
public class AuthorRepository extends SimpleJpaRepository<Author, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public AuthorRepository(EntityManager em) {
        super(Author.class, em);
    }

    public Page<Author> getAuthorsz(Pageable pageable, Specification<Author> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cbQuery = cb.createQuery(Author.class);

        Root<Author> root = cbQuery.from(Author.class);

        cbQuery.where(spec.toPredicate(root, cbQuery, cb));
        cbQuery.orderBy(toOrders(pageable.getSort(), root, cb));

        TypedQuery<Author> query = entityManager.createQuery(cbQuery);

        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        Page<Author> page = PageableExecutionUtils.getPage(query.getResultList(),
                pageable,
                () -> getCountQuery(spec, Author.class).getResultList()
                        .stream().filter(Objects::nonNull).mapToLong(Long::longValue).sum());
        return page;

    }

    protected Map<Long, List<Long>> readNonManagedManyToManyLinkTable(String tableName, String leftColumnName,
                                                                      String rightColumnName, List<Long> ids){
        StringJoiner q = new StringJoiner("")
                .add("select ")
                .add(leftColumnName)
                .add(",")
                .add(rightColumnName)
                .add(" from ")
                .add(tableName)
                .add(" where ")
                .add(leftColumnName)
                .add(" in(?1)");

        Query imagesResolution = entityManager.createNativeQuery(q.toString());
        imagesResolution.setParameter(1, ids);
        List<Object[]> resultList = imagesResolution.getResultList();
        return resultList.stream()
                .collect(Collectors.groupingBy(arr -> ((BigInteger) arr[0]).longValue(),
                        Collectors.mapping(arr -> ((BigInteger) arr[1]).longValue(), Collectors.toList())));
    }


    public Page<AuthorProjection> getAuthorProjectionzz(Pageable pageable, Specification<Author> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AuthorProjection> cbQuery = cb.createQuery(AuthorProjection.class);

        Root<Author> root = cbQuery.from(Author.class);
//        ListJoin booksCollection = root.joinList(Author_.books.getName());
//        ListJoin<Author, Book> join = root.join(Author_.books);
//        ListAttribute<Author, Book> books = Author_.books;
        cbQuery.multiselect(
                root.get(Author_.id),
                root.get(Author_.name)
//                booksCollection.get(Book_.id)
//                join.get(Book_.id)
        );

//        Predicate equality = cb.equal(root.get(Author_.id), id);
        cbQuery.where(spec.toPredicate(root, cbQuery, cb));
        cbQuery.orderBy(toOrders(pageable.getSort(), root, cb));

        TypedQuery<AuthorProjection> query = entityManager.createQuery(cbQuery);

        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        Page<AuthorProjection> projectionsPage = PageableExecutionUtils.getPage(query.getResultList(),
                pageable,
                () -> getCountQuery(spec, Author.class).getResultList()
                        .stream().filter(Objects::nonNull).mapToLong(Long::longValue).sum());

//        page.map(tuplePage -> tuplePage.getElements().stream()
//                .collect(
//                        Collectors.groupingBy(tuple -> (Long) tuple.get(0),
//                                Collectors.mapping(tuple -> (Long) tuple.get(1), Collectors.toList())
//                        )
//                ))


        List<Long> retrievedAuthorsIds = projectionsPage.getContent().stream().map(AuthorProjection::getId).collect(Collectors.toList());

        Map<Long, List<Long>> imageIds = readNonManagedManyToManyLinkTable("author_books","author_id","books_id",retrievedAuthorsIds);
        projectionsPage.map(projection->{
            projection.setBooksIds(imageIds.get(projection.getId()));
            return projection;
        });
        System.err.println(projectionsPage.getContent());

        return projectionsPage;

    }
}
