package my.app.enumsaving;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "enum_gender")
//@Convert(attributeName = "value", converter = GenderConverter.class)
public class Gender extends SourceProfileField<GenderType> {

    @Override
    @Enumerated(EnumType.STRING)
    public GenderType getValue() {
        return super.getValue();
    }

    @Override
    @Enumerated(EnumType.STRING)
    public void setValue(GenderType value) {
        super.setValue(value);
    }
}