package my.app.enumsaving;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum GenderType {
    @Enumerated(EnumType.STRING)
    male,
    @Enumerated(EnumType.STRING)
    female

}