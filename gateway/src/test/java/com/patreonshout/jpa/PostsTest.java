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
     * tests getExistingPosts() from {@link com.patreonshout.jpa.PostsRepository} that will return a list of posts that are already in the database from the list of posts given in the parameter
     */
    @Test
    public void existingPostsTest() { // TODO

        List<PostBean> pbList = new ArrayList<>();

        postsRepository.getExistingPosts(pbList);
    }



}
