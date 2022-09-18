package PS.jpa;

import PS.beans.PostBean;
import PS.config.TestConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, Posts.class, PostsRepository.class})
@ActiveProfiles("test")
@Transactional
public class PostsTest {

    @Autowired
    Posts posts;

    @Test
    public void putPostAndGetAllPostsTest() {
        PostBean pb1 = new PostBean();
        pb1.setIsprivate(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        posts.putPost(pb1);

        Assert.assertEquals(1, posts.getAllPosts().size());

        PostBean pb2 = new PostBean();
        pb1.setIsprivate(false);
        pb1.setContent("content2");
        pb1.setPublishdate("date2");
        pb1.setCreator("creator2");
        pb1.setTitle("title2");
        pb1.setUrl("url2");
        posts.putPost(pb2);

        Assert.assertEquals(2, posts.getAllPosts().size());
    }

    @Test
    public void getPostTest() {
        PostBean pb1 = new PostBean();
        pb1.setIsprivate(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        posts.putPost(pb1);

        PostBean pb2 = posts.getPost("url1");

        Assert.assertEquals(pb1.isIsprivate(), pb2.isIsprivate());
        Assert.assertEquals(pb1.getContent(), pb2.getContent());
        Assert.assertEquals(pb1.getPublishdate(), pb2.getPublishdate());
        Assert.assertEquals(pb1.getTitle(), pb2.getTitle());
        Assert.assertEquals(pb1.getUrl(), pb2.getUrl());
    }

    @Test
    public void updatePostTest() {
        PostBean pb1 = new PostBean();
        pb1.setIsprivate(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        posts.putPost(pb1);

        PostBean pb2 = posts.getPost("url1");
        pb2.setUrl("url2");
        posts.updatePost(pb2);

        Assert.assertEquals("url2", posts.getPost("url2").getUrl());
    }

    @Test
    public void removePostTest() {
        PostBean pb1 = new PostBean();
        pb1.setIsprivate(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        posts.putPost(pb1);

        Assert.assertEquals(1, posts.getAllPosts().size());

        posts.removePost("url1");

        Assert.assertEquals(0, posts.getAllPosts().size());
    }

}
