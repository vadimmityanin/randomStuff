package my.app.onetomany.bidirectional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class BaseRepository<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements QueryDSLRepository<T, ID>{

    protected final EntityManager entityManager;
    protected final Querydsl querydsl;

    @SuppressWarnings("unchecked")
    public BaseRepository(Class<T> klass, EntityManager entityManager) {
        super((JpaEntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(klass, entityManager),
                entityManager,
                SimpleEntityPathResolver.INSTANCE);
        this.entityManager = entityManager;

        EntityPath<T> path = SimpleEntityPathResolver.INSTANCE.createPath(
                JpaEntityInformationSupport.getEntityInformation(klass, entityManager).getJavaType());
        PathBuilder<T> builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    public <DTO> Page<DTO> findAllDTOs(Predicate predicate, Pageable pageable, FactoryExpression<DTO> factoryExpression) {
        JPQLQuery<DTO> query = createQuery(predicate).select(factoryExpression);
        querydsl.applyPagination(pageable, query);
        querydsl.applySorting(pageable.getSort(), query);

        QueryResults<DTO> queryResults = query.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

}
