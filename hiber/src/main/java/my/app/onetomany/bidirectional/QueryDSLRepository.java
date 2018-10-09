package my.app.onetomany.bidirectional;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueryDSLRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    <DTO> Page<DTO> findAllDTOs(Predicate predicate, Pageable pageable, FactoryExpression<DTO> factoryExpression);
}

