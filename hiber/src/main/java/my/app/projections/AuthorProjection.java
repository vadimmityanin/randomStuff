package my.app.projections;

import java.util.List;
import java.util.StringJoiner;

public class AuthorProjection {

    private long id;
    private String name;

    private List<Long> booksIds;

    public AuthorProjection() {
    }

    public AuthorProjection(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(List<Long> booksIds) {
        this.booksIds = booksIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthorProjection.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("booksIds=" + booksIds)
                .toString();
    }
}
