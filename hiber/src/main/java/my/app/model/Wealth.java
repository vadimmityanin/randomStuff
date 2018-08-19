package my.app.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.net.URL;


@Entity
@AttributeOverride(name = "value", column = @Column(nullable = false))
public class Wealth extends ProfileField<URL>  {


}
