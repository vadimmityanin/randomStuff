package my.app.onetomany.bidirectional;

public interface HomeRepository extends QueryDSLRepository<Home,Long>, HomeRepositoryCustom {
}
