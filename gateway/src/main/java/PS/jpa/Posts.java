package PS.jpa;

import PS.beans.PostBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Posts {

    @Autowired
    PostsRepository postsRepository;

    public PostBean getPost(String url){
        return postsRepository.getPost(url);
    }

    public List<PostBean> getAllPosts() {
        return postsRepository.getAllPosts();
    }

    public void putPost(PostBean pb) {
        postsRepository.putPost(pb);
    }

    public void updatePost(PostBean pb) {
        postsRepository.updatePost(pb);
    }

    public void removePost(String url) {
        postsRepository.removePost(url);
    }

}
