package my.app.deepGraphGenericEntities;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "marital_status")
public class MaritalStatus extends SocialInformationField<String> {

}
