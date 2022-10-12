package com.patreonshout.rest;

import com.patreonshout.beans.PatreonInfoBean;
import com.patreonshout.rest.interfaces.PatreonInfoImpl;
import com.patreonshout.jpa.PatreonInfo;
import com.patreonshout.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Patreon Info RESTful Endpoint Interface
 *
 * <p>
 *     Responsibilities:
 *     1) Create, read, update, and delete items from our patreon_info table in our database
 *     2) Direct communication between the frontend portion of our application with the backend
 * </p>
 */
@RestController
public class PatreonInfoSvc implements PatreonInfoImpl {

    /**
     * patreonInfo is the wrapper class for {@link com.patreonshout.jpa.PatreonInfoRepository}
     */
    @Autowired
    PatreonInfo patreonInfo;

    /**
     * Endpoint to put an object containing a content creator's patreon information to our database
     *
     * @param request is the json request information passed to the endpoint
     * @return either "200" - success or "400" - failure
     */
    public String PutPatreonInfo(@RequestBody String request) {

        PatreonInfoBean pib = (PatreonInfoBean) JSONUtil.getBeanFromJSON(request, PatreonInfoBean.class);

        if (pib == null) {
            return "400";
        }

        return patreonInfo.putPatreonInfo(pib);
    }


}
