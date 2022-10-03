package com.patreonshout.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful Endpoint Interface
 *
 * <p>
 *     Responsibilities:
 *     1) Create, read, update, and delete items from our database
 *     2) Direct communication between the frontend portion of our application with the backend
 * </p>
 */
@RestController
@RequestMapping(value = "/v1")
public class RestService {

    /**
     * restCommon is the layer in between the different classes we use to talk to the database and the endpoints, any
     * preliminary steps that we may need to do are done in {@link com.patreonshout.rest.RestCommon}
     */
    @Autowired
    RestCommon restCommon;


    /**
     * Endpoint to put an object containing a content creator's patreon information to our database
     *
     * @param request is the json request information passed to the endpoint
     * @return either "success" or "failure"
     */
    @PutMapping(value = "/putPatreonInfo", consumes = "application/json")
    public String putPatreonInfo(@RequestBody String request) {
        return restCommon.putPatreonInfo(request);
    }


}
