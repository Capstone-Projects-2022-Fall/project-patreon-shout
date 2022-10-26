package com.patreonshout.rest;

import com.patreonshout.jpa.ListsRepository;
import com.patreonshout.jpa.WebAccount;
import com.patreonshout.rest.interfaces.ListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lists RESTful Endpoint Interface
 */
@RestController
public class ListSvc extends BaseSvc implements ListImpl {


    @Autowired
    private ListsRepository listsRepository;

    @Autowired
    private WebAccount webAccount;


    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserLists(@RequestParam(name = "loginToken") String loginToken) {

        return null;
    }
}
