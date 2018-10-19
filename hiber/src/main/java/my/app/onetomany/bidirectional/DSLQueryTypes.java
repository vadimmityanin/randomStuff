package my.app.onetomany.bidirectional;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import my.app.manytomany.QDocument;

public class DSLQueryTypes {
   public static final QHome HOME = QHome.home;
   public static final QFloor FLOOR = QFloor.floor;
   public static final QUnitDepartment DEPARTMENT = QUnitDepartment.unitDepartment;

    public static final ConstructorExpression<Long> LONG_PROJ = Projections.constructor(Long.class,
            QDocument.document.id
    );
}
