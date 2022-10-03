package com.patreonshout.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class RestService {

    @Autowired
    RestCommon restCommon;

    @PutMapping(value = "/putPatreonInfo", consumes = "application/json")
    public String putPatreonInfo(@RequestBody String request) {
        return restCommon.putPatreonInfo(request);
    }


}
