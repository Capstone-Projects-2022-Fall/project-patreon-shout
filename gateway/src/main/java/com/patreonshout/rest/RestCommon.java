package com.patreonshout.rest;

import com.patreonshout.beans.PatreonInfoBean;
import com.patreonshout.jpa.PatreonInfo;
import com.patreonshout.utils.JSONUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestCommon {

    @Autowired
    PatreonInfo patreonInfo;

    public String putPatreonInfo(String request) {


        PatreonInfoBean pib = (PatreonInfoBean) JSONUtil.getBeanFromJSON(request, PatreonInfoBean.class);

        if (pib == null) {
            return "failure";
        }

        return patreonInfo.putPatreonInfo(pib);
    }

}
