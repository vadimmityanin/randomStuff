package my.app.jsonb;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Docz {

    @Id
    private long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Signed jsonchik;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Signed getJsonchik() {
        return jsonchik;
    }

    public void setJsonchik(Signed jsonchik) {
        this.jsonchik = jsonchik;
    }
}
