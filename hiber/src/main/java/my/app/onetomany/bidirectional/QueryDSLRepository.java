package my.app.onetomany.bidirectional;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface QueryDSLRepository<T, ID> extends CrudRepository<T, ID>, QuerydslPredicateExecutor<T> {

    <DTO> Page<DTO> findDTOsPage(Predicate predicate, Pageable pageable, FactoryExpression<DTO> factoryExpression);
    <DTO> Page<DTO> findDTOsPage(JPAQuery<DTO> query, Pageable pageable, FactoryExpression<DTO> factoryExpression);
    <DTO> List<DTO> findDTO(Predicate predicate, FactoryExpression<DTO> factoryExpression);
}

