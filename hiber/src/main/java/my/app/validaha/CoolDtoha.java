package my.app.validaha;

import my.app.validaha.val.CoolzDTOHA;

import javax.validation.constraints.Size;
import java.util.StringJoiner;

@CoolzDTOHA
public class CoolDtoha {

        @Size(min = 4)
    private String name;

        @Size(min = 4)
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoolDtoha.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .toString();
    }
}
