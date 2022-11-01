package com.patreonshout.rest;

import com.patreonshout.beans.ListBean;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.ListCreationRequest;
import com.patreonshout.beans.request.ListDeleteRequest;
import com.patreonshout.beans.request.ListUpdateRequest;
import com.patreonshout.jpa.ListsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.ListImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> GetUserLists(@RequestParam(name = "loginToken") String loginToken) {
        WebAccount userAccount = webAccountFunctions.findByLoginToken(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (ListBean lb : userAccount.getListBean()) {
            Map<String, String> listResponse = new HashMap<>();

            listResponse.put("webaccount_id", String.valueOf(lb.getWebAccount().getWebAccountId()));
            listResponse.put("title", lb.getTitle());
            listResponse.put("description", lb.getDescription());
            listResponse.put("added_creators", lb.getAdded_creators());
            listResponse.put("list_id", String.valueOf(lb.getList_id()));

            response.add(listResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> AddUserList(@RequestBody ListCreationRequest listCreationRequest) {
        WebAccount userAccount = webAccountFunctions.findByLoginToken(listCreationRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        ListBean lb = new ListBean();
        lb.setTitle(listCreationRequest.getTitle());
        lb.setDescription(listCreationRequest.getDescription());
        lb.setAdded_creators(listCreationRequest.getAdded_creators());
        lb.setWebAccount(userAccount);

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.CREATED, "List created.");
    }

    /**
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> UpdateUserList(@RequestBody ListUpdateRequest listUpdateRequest) {
        ListBean lb = listsRepository.getListByList_id(listUpdateRequest.getList_id());

        if (!lb.getWebAccount().getLoginToken().equals(listUpdateRequest.getLoginToken())) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
        }

        lb.setTitle(listUpdateRequest.getTitle());
        lb.setDescription(listUpdateRequest.getDescription());
        lb.setAdded_creators(listUpdateRequest.getAdded_creators());

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.OK, "List updated.");
    }

    /**
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> DeleteUserList(@RequestBody ListDeleteRequest listDeleteRequest) {
        ListBean lb = listsRepository.getListByList_id(listDeleteRequest.getList_id());

        if(!lb.getWebAccount().getLoginToken().equals(listDeleteRequest.getLoginToken())) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
        }

        listsRepository.delete(lb);

        return ResponseUtil.Generic(HttpStatus.OK, "List removed.");
    }
}
