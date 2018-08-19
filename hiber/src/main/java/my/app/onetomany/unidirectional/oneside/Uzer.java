package my.app.onetomany.unidirectional.oneside;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Uzer {
    //u cannot just add new post to collection and try to save Uzer,
    //umust save post before (it is one-way relationship)

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    @JoinColumn(name = "user_id")//this says that post_id going to be created
    // INDEED in Post table!!! and going to serve as FK to Uzer
    private List<Post> posts = new ArrayList<>();

//    public void addPost(Post post){
//        posts.add(post);
//        post.
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
