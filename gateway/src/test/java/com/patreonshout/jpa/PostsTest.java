package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Testing the Posts class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
public class PostsTest {

    /**
     * posts is the reference to the {@link com.patreonshout.jpa.PostsRepository} object that is the wrapper class to the {@link PostsRepositoryImpl}
     * that handles the communication to the database's posts table
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * tests putPost() and getAllPosts() from {@link com.patreonshout.jpa.PostsRepository} that puts a PostBean into the database
     * and gets every post in the database respectively
     */
    @Test
    public void putPostAndGetAllPostsTest() {
        PostBean pb1 = new PostBean();
        pb1.set_public(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator_page_url("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        postsRepository.putPost(pb1);

        Assert.assertEquals(1, postsRepository.getAllPosts().size());

        PostBean pb2 = new PostBean();
        pb1.set_public(false);
        pb1.setContent("content2");
        pb1.setPublishdate("date2");
        pb1.setCreator_page_url("creator2");
        pb1.setTitle("title2");
        pb1.setUrl("url2");
        postsRepository.putPost(pb2);

        Assert.assertEquals(2, postsRepository.getAllPosts().size());
    }

    /**
     * tests getPost() from {@link com.patreonshout.jpa.PostsRepository} that gets a specific post from the database based on a given url
     */
    @Test
    public void getPostTest() {
        PostBean pb1 = new PostBean();
        pb1.set_public(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator_page_url("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        postsRepository.putPost(pb1);

        PostBean pb2 = postsRepository.getPost("url1");

        Assert.assertEquals(pb1.is_public(), pb2.is_public());
        Assert.assertEquals(pb1.getContent(), pb2.getContent());
        Assert.assertEquals(pb1.getPublishdate(), pb2.getPublishdate());
        Assert.assertEquals(pb1.getTitle(), pb2.getTitle());
        Assert.assertEquals(pb1.getUrl(), pb2.getUrl());
    }

    /**
     * tests updatePost() from {@link com.patreonshout.jpa.PostsRepository} that updates a post in the database based on a post_id when provided a PostBean
     */
    @Test
    public void updatePostTest() {
        PostBean pb1 = new PostBean();
        pb1.set_public(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator_page_url("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        postsRepository.putPost(pb1);

        PostBean pb2 = postsRepository.getPost("url1");
        pb2.setUrl("url2");
        postsRepository.updatePost(pb2);

        Assert.assertEquals("url2", postsRepository.getPost("url2").getUrl());
    }

    /**
     * tests removePost() from {@link com.patreonshout.jpa.PostsRepository} that will remove a given post from the database given a url
     */
    @Test
    public void removePostTest() {
        PostBean pb1 = new PostBean();
        pb1.set_public(false);
        pb1.setContent("content1");
        pb1.setPublishdate("date1");
        pb1.setCreator_page_url("creator1");
        pb1.setTitle("title1");
        pb1.setUrl("url1");
        postsRepository.putPost(pb1);

        Assert.assertEquals(1, postsRepository.getAllPosts().size());

        postsRepository.removePost("url1");

        Assert.assertEquals(0, postsRepository.getAllPosts().size());
    }

    @Test
    public void existingPostsTest() {

        List<PostBean> pbList = new ArrayList<>();

        postsRepository.getExistingPosts(pbList);
    }



}
