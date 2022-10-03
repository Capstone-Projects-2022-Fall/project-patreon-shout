package com.patreonshout.rest;

import com.patreonshout.beans.PatreonInfoBean;
import com.patreonshout.jpa.PatreonInfo;
import com.patreonshout.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RESTful Endpoint Implementation
 *
 * <p>
 *     Responsibilities:
 *     1) Be the layer between the endpoint interface and the processing of the individual endpoint transactions
 *     2) Process preliminary database communication steps
 * </p>
 *
 */
@Component
public class RestCommon {

    /**
     * patreonInfo is the wrapper class for {@link com.patreonshout.jpa.PatreonInfoRepository}
     */
    @Autowired
    PatreonInfo patreonInfo;

    /**
     * Initializes a {@link com.patreonshout.beans.PatreonInfoBean} based on the json information provided then sends the
     * object to patreonInfo for processing
     *
     * @param request is the json request information passed to the endpoint
     * @return either "success" or "failure"
     */
    public String putPatreonInfo(String request) {
        PatreonInfoBean pib = (PatreonInfoBean) JSONUtil.getBeanFromJSON(request, PatreonInfoBean.class);

        if (pib == null) {
            return "failure: cannot map variables to [" + PatreonInfoBean.class.getSimpleName() + "]";
        }

        return patreonInfo.putPatreonInfo(pib);
    }

}
