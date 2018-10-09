package my.app.enumsaving;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enum_nationality")
public class Nationality extends SourceProfileField<String> {

}
