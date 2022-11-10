package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.ListBean;
import com.patreonshout.beans.ListPost;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.WebAccount;
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

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (ListBean lb : userAccount.getListBean()) {
            Map<String, String> listResponse = new HashMap<>();

            listResponse.put("webaccount_id", String.valueOf(lb.getWebAccount().getWebAccountId()));
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

        ListBean lb = new ListBean();
        lb.setTitle(listCreationRequest.getTitle());
        lb.setDescription(listCreationRequest.getDescription());
        lb.setWebAccount(userAccount);

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.CREATED, "List created.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> UpdateUserList(@RequestBody ListUpdateRequest listUpdateRequest) {
        ListBean lb = listsRepository.getListByList_id(listUpdateRequest.getList_id());

        if (!lb.getWebAccount().getLoginToken().equals(listUpdateRequest.getLoginToken())) {
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
    public ResponseEntity<?> DeleteUserList(@RequestBody ListDeleteRequest listDeleteRequest) {
        ListBean lb = listsRepository.getListByList_id(listDeleteRequest.getList_id());

        // TODO: fix the error messages for this (check login token first, then check if list exists) dont give any info for potential attacker
        try {
            if(!lb.getWebAccount().getLoginToken().equals(listDeleteRequest.getLoginToken())) {
                return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
            }
        }
        catch (NullPointerException e) {
            return ResponseUtil.Generic(HttpStatus.OK, "List removed if the list existed");
        }


        System.out.println("lb: " + lb);
        System.out.println("wb: " + lb.getWebAccount());
        // TODO: delete all posts in list_posts associated with this list
        listsRepository.deleteListByList_id(listDeleteRequest.getList_id());

        return ResponseUtil.Generic(HttpStatus.OK, "List removed if the list existed.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserListsWithPost(String loginToken, String url) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        // if no post matches to the databased, then return as if we didn't find any matching posts
        if (postsRepository.findPostBeanByUrl(url) == null) {
            return new ResponseEntity<>("[]", HttpStatus.FOUND);
        }

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (ListBean lb : userAccount.getListBean()) {
            for (ListPost lp : lb.getListPosts()) {
                if (lp.getPost().getUrl().equals(url)) {
                    Map<String, String> listResponse = new HashMap<>();

                    listResponse.put("title", lb.getTitle());
                    listResponse.put("description", lb.getDescription());
                    listResponse.put("list_id", String.valueOf(lb.getListId()));

                    response.add(listResponse);
                }
            }
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    public ResponseEntity<?> UpdateUserPostLists(ListPostUpdateRequest listPostUpdateRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(listPostUpdateRequest.getLoginToken());

        // if no post matches to the databased, then return as if we didn't find any matching posts
        PostBean pb;
        if ((pb = postsRepository.findPostBeanByUrl(listPostUpdateRequest.getUrl())) == null) {
            return new ResponseEntity<>("Post lists updated.", HttpStatus.OK);
        }

        for (ListPostUpdateRequest.ListUpdate listUpdate : listPostUpdateRequest.getListUpdates()) {
            ListBean lb = listsRepository.getListByList_id(listUpdate.getListId());

            ListPost listPost = new ListPost();
            listPost.setList(lb);
            listPost.setPost(pb);

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

}
