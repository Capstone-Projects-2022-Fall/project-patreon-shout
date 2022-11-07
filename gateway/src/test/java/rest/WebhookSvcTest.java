package rest;

import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.rest.WebhookSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Testing the WebhookSvc Endpoint
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DataJpaTest
public class WebhookSvcTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void testOAuthSavePosts() throws IOException {
        String accessToken = "";
        String creatorPageUrl = "";

        WebhookSvc webhookSvc = new WebhookSvc();

//        webhookSvc.savePosts(accessToken, creatorPageUrl);



    }


}
