package com.patreonshout.rest;

import com.patreonshout.beans.ListBean;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.beans.request.ListCreationRequest;
import com.patreonshout.beans.request.ListUpdateRequest;
import com.patreonshout.jpa.ListsRepository;
import com.patreonshout.jpa.NewWebAccountRepository;
import com.patreonshout.rest.interfaces.ListImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Lists RESTful Endpoint Interface
 */
@RestController
public class ListSvc extends BaseSvc implements ListImpl {


    @Autowired
    private ListsRepository listsRepository;

    @Autowired
    private NewWebAccountRepository newWebAccountRepository;


    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserLists(@RequestParam(name = "loginToken") String loginToken) {
        WebAccountBean userAccount = newWebAccountRepository.findByLoginToken(loginToken);

        List<ListBean> lb = listsRepository.findByListId(userAccount.getWebAccountId());

        return new ResponseEntity<>(lb, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> AddUserList(@RequestBody ListCreationRequest listCreationRequest) {
        WebAccountBean userAccount = newWebAccountRepository.findByLoginToken(listCreationRequest.getLoginToken());

        ListBean lb = new ListBean();
        lb.setTitle(listCreationRequest.getTitle());
        lb.setDescription(listCreationRequest.getDescription());
        lb.setAdded_creators(listCreationRequest.getAdded_creators());
        lb.setListId(userAccount.getWebAccountId());

        listsRepository.save(lb);

        return ResponseUtil.Generic(HttpStatus.CREATED, "List created.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> UpdateUserList(@RequestBody ListUpdateRequest listUpdateRequest) {

        // TODO: need to add list_id(pk) to ListBean/database so i can delete a specific list of a user rather than all their lists

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> DeleteUserList(@RequestParam String list_id) {

        // TODO: need to add list_id(pk) to ListBean/database so i can delete a specific list of a user rather than all their lists
        return null;
    }
}
