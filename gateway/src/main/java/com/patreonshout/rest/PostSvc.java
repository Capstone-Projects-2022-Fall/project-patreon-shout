package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorPage;
import com.patreonshout.beans.FollowingCreators;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.PostGetMultipleRequest;
import com.patreonshout.jpa.CreatorPageFunctions;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.PostImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Post RESTful Endpoint Interface
 */
@RestController
public class PostSvc extends BaseSvc implements PostImpl {

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the posts table in the database
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the creator_pages table in the database
     */
    @Autowired
    CreatorPageFunctions creatorPageFunctions;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private WebAccountFunctions webAccountFunctions;



    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetCreatorPosts(@RequestParam(name = "campaign") int campaignId) {
        List<PostBean> posts = postsRepository.findAllByCampaignId(campaignId);
        CreatorPage campaign = creatorPageFunctions.getCreatorPage(campaignId);

        List<Map<String, String>> response = new ArrayList<>();

        for (PostBean pb : posts) {
            if (!pb.getIsPublic()) {
                pb.setContent("This post is private");
            }

            Map<String, String> listResponse = new HashMap<>();
            listResponse.put("title", pb.getTitle());
            listResponse.put("campaign_id", String.valueOf(pb.getCampaignId()));
            listResponse.put("url", pb.getUrl());
            listResponse.put("content", pb.getContent());
            listResponse.put("published_at", pb.getPublishedAt());
            listResponse.put("is_public", String.valueOf(pb.getIsPublic()));
            listResponse.put("creator_name", campaign.getPageName());

            response.add(listResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetMultipleCreatorPosts(@RequestBody PostGetMultipleRequest postGetMultipleRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(postGetMultipleRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        Page<PostBean> page = postsRepository.getMultipleCreatorPosts(postGetMultipleRequest.getCreators(), PageRequest.of(postGetMultipleRequest.getPage(), 5).withSort(Sort.Direction.ASC, "published_at"));

        for (PostBean pb : page.getContent()) {
            if (!pb.getIsPublic()) {
                pb.setContent("This post is private");
            }
        }

        // TODO: might need to construct response since PostBean is related to other beans and jackson cannot correctly get the data from the other beans relating to PostBean (look at the endpoint above)
        return new ResponseEntity<>(page, HttpStatus.FOUND);
    }

    public ResponseEntity<?> GetCampaignInfoFromId(int campaignId, String loginToken) throws PSException {

        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }


        CreatorPage campaign = creatorPageFunctions.getCreatorPage(campaignId);

        if (campaign == null) {
            return ResponseUtil.Generic(HttpStatus.NOT_FOUND, "No campaign found.");
        }

        return new ResponseEntity<>(campaign, HttpStatus.FOUND);
    }

    public ResponseEntity<?> GetFollowingPosts(String loginToken) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        FollowingCreators followingCreators = userAccount.getFollowingCreators();

        if (followingCreators == null || followingCreators.getCampaignIds().equals("[]")) {
            return new ResponseEntity<>("[]", HttpStatus.FOUND);
        }

        String[] followingCampaigns = followingCreators.getCampaignIds().replaceAll("\\[|]|\\s", "").split(",");

        HashMap<Integer, String> campaignToCreatorPageUrl = new HashMap<>();
        for (String campaignId : followingCampaigns) {
            CreatorPage campaign = creatorPageFunctions.getCreatorPage(Integer.parseInt(campaignId));
            campaignToCreatorPageUrl.put(Integer.parseInt(campaignId), (campaign != null ? campaign.getPageName() : "null"));
        }

        List<Map<String, String>> response = new ArrayList<>();
        for (PostBean pb : postsRepository.getMultipleCreatorPosts(Arrays.asList(followingCampaigns))) {
            if (!pb.getIsPublic()) {
                pb.setContent("This post is private");
            }

            Map<String, String> listResponse = new HashMap<>();
            listResponse.put("title", pb.getTitle());
            listResponse.put("campaign_id", String.valueOf(pb.getCampaignId()));
            listResponse.put("url", pb.getUrl());
            listResponse.put("content", pb.getContent());
            listResponse.put("published_at", pb.getPublishedAt());
            listResponse.put("is_public", String.valueOf(pb.getIsPublic()));
            listResponse.put("creator_name", campaignToCreatorPageUrl.get(pb.getCampaignId()));

            response.add(listResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
