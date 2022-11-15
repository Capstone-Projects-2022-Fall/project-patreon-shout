package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.ListBean;
import com.patreonshout.beans.ListPost;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.FavoriteListRequest;
import com.patreonshout.beans.request.ListCreationRequest;
import com.patreonshout.beans.request.ListDeleteRequest;
import com.patreonshout.beans.request.ListPostUpdateRequest;
import com.patreonshout.beans.request.ListUpdateRequest;
import com.patreonshout.jpa.ListPostsRepository;
import com.patreonshout.jpa.ListsRepository;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.ListImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Lists RESTful Endpoint Interface
 */
@RestController
public class ListSvc extends BaseSvc implements ListImpl {


    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the lists table in the database
     */
    @Autowired
    private ListsRepository listsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private WebAccountFunctions webAccountFunctions;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private PostsRepository postsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private ListPostsRepository listPostsRepository;

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserLists(@RequestParam(name = "loginToken") String loginToken) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }


        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (ListBean lb : listsRepository.findListBeansByWebAccountId(userAccount.getWebAccountId())) {
            Map<String, String> listResponse = new HashMap<>();

            listResponse.put("webaccount_id", String.valueOf(lb.getWebAccountId()));
            listResponse.put("title", lb.getTitle());
            listResponse.put("description", lb.getDescription());
            listResponse.put("list_id", String.valueOf(lb.getListId()));

            response.add(listResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> AddUserList(@RequestBody ListCreationRequest listCreationRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(listCreationRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean lb = new ListBean();
        lb.setTitle(listCreationRequest.getTitle());
        lb.setDescription(listCreationRequest.getDescription());
        lb.setWebAccountId(userAccount.getWebAccountId());

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.CREATED, "List created.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> UpdateUserList(@RequestBody ListUpdateRequest listUpdateRequest) {
        ListBean lb = listsRepository.getListByListId(listUpdateRequest.getList_id());

        if (!webAccountFunctions.findByWebAccountId(lb.getWebAccountId()).getLoginToken().equals(listUpdateRequest.getLoginToken())) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
        }

        lb.setTitle(listUpdateRequest.getTitle());
        lb.setDescription(listUpdateRequest.getDescription());

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.OK, "List updated.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> DeleteUserList(@RequestBody ListDeleteRequest listDeleteRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(listDeleteRequest.getLoginToken());
        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean lb = listsRepository.getListByListId(listDeleteRequest.getList_id());

        try {
            if(!webAccountFunctions.findByWebAccountId(lb.getWebAccountId()).getLoginToken().equals(listDeleteRequest.getLoginToken())) {
                return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
            }
        }
        catch (NullPointerException e) {
            return ResponseUtil.Generic(HttpStatus.OK, "List removed if the list existed");
        }

        for (ListPost listPost : listPostsRepository.findAllByListId(lb.getListId())) {
            Optional<PostBean> pb = postsRepository.findById(listPost.getPostId());

            if (pb.isEmpty()) {
                return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Cannot find post.");
            }

            listPostsRepository.deleteByListAndPost(lb.getListId(), pb.get().getPostId());
        }

        listsRepository.deleteListByListId(listDeleteRequest.getList_id());

        return ResponseUtil.Generic(HttpStatus.OK, "List removed if the list existed.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserListsWithPost(String loginToken, String url) throws PSException { // TODO: make more efficient
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        // if no post matches to the databased, then return as if we didn't find any matching posts
        PostBean pb;
        if ((pb = postsRepository.findPostBeanByUrl(url)) == null) {
            return new ResponseEntity<>("[]", HttpStatus.FOUND);
        }

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        // find all the lists that this post is in

        /*
            post_id (find all lists where post_id = :post_id)


            want list_id, title, desc
         */


        for (ListPost lp : listPostsRepository.findAllByPostId(pb.getPostId())) {

            Optional<ListBean> optionalList = listsRepository.findById(lp.getListId());
            if (optionalList.isEmpty()) {
                return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Cannot find list.");
            }

            ListBean list = optionalList.get();

            WebAccount account = webAccountFunctions.findByWebAccountId(list.getWebAccountId());

            if (account.getLoginToken().equals(loginToken)) {
                Map<String, String> listResponse = new HashMap<>();

                listResponse.put("title", list.getTitle());
                listResponse.put("description", list.getDescription());
                listResponse.put("list_id", String.valueOf(list.getListId()));

                response.add(listResponse);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> UpdateUserPostLists(ListPostUpdateRequest listPostUpdateRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(listPostUpdateRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        // if no post matches to the databased, then return as if we didn't find any matching posts
        PostBean pb;
        if ((pb = postsRepository.findPostBeanByUrl(listPostUpdateRequest.getUrl())) == null) {
            return new ResponseEntity<>("Post lists updated.", HttpStatus.OK);
        }

        for (ListPostUpdateRequest.ListUpdate listUpdate : listPostUpdateRequest.getListUpdates()) {
            ListBean lb = listsRepository.getListByListId(listUpdate.getListId());

            ListPost listPost = new ListPost();
            listPost.setListId(lb.getListId());
            listPost.setPostId(pb.getPostId());

            if (listUpdate.isUpdate()) {
                try {
                    listPostsRepository.save(listPost);
                }
                catch (Exception e) { // if we try to save a duplicate post it will print the stack trace
                    e.printStackTrace();
                }

            }
            else {
                listPostsRepository.deleteByListAndPost(lb.getListId(), pb.getPostId());
            }
        }


        return ResponseUtil.Generic(HttpStatus.OK, "Post lists updated.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetPostsFromList(String loginToken, int list_id) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean lb = listsRepository.getListByListId(list_id);
        List<ListPost> listPosts = listPostsRepository.findAllByListId(lb.getListId());

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (ListPost lp : listPosts) {
            Map<String, String> postResponse = new HashMap<>();

            Optional<PostBean> optionalPost = postsRepository.findById(lp.getPostId());

            if (optionalPost.isPresent()) {
                PostBean pb = optionalPost.get();

                postResponse.put("published_at", pb.getPublishedAt());
                postResponse.put("title", pb.getTitle());
                postResponse.put("url", pb.getUrl());
                postResponse.put("is_public", String.valueOf(pb.getIsPublic()));
                postResponse.put("app_id", pb.getAppId());
                postResponse.put("app_status", pb.getAppStatus());
                postResponse.put("embed_data", pb.getEmbedData());
                postResponse.put("embed_url", pb.getEmbedUrl());
                postResponse.put("is_paid", String.valueOf(pb.getIsPaid()));

                if (pb.getIsPublic()) {
                    postResponse.put("content", pb.getContent());
                }
                else {
                    postResponse.put("content", "This post is private");
                }

                response.add(postResponse);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> AddPostToFavoritesList(FavoriteListRequest favoriteListRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(favoriteListRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean favList = listsRepository.findListBeanByWebAccountIdAndTitle(userAccount.getWebAccountId(), "Favorites");

        // if there is no favorites list, then create one
        if (favList == null) {
            favList = new ListBean();

            favList.setWebAccountId(userAccount.getWebAccountId());
            favList.setTitle("Favorites");
            favList.setDescription("Your favorite posts");

            listsRepository.save(favList);
            favList = listsRepository.findListBeanByWebAccountIdAndTitle(userAccount.getWebAccountId(), "Favorites"); // if it is a new list, make sure we get the newly assigned list_id from the database
        }

        ListPost listPost = new ListPost();
        listPost.setListId(favList.getListId());

        PostBean favPost = postsRepository.findPostBeanByUrl(favoriteListRequest.getUrl());
        listPost.setPostId(favPost.getPostId());

        listPostsRepository.save(listPost);
        System.out.println("added");

        return ResponseUtil.Generic(HttpStatus.OK, "Favorites lists updated.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> DeletePostFromFavoritesList(FavoriteListRequest favoriteListRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(favoriteListRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean favList = listsRepository.findListBeanByWebAccountIdAndTitle(userAccount.getWebAccountId(), "Favorites");

        // if there is no favorites list
        if (favList == null) {
            return ResponseUtil.Generic(HttpStatus.OK, "Favorites lists updated.");
        }

        PostBean favPost = postsRepository.findPostBeanByUrl(favoriteListRequest.getUrl());

        listPostsRepository.deleteByListAndPost(favList.getListId(), favPost.getPostId());
        System.out.println("deleted");

        return ResponseUtil.Generic(HttpStatus.OK, "Favorites lists updated.");
    }

}
