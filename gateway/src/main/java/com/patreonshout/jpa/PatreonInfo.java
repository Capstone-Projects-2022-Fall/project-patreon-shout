package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatreonInfo {

    @Autowired
    PatreonInfoRepository patreonInfoRepository;

    public String putPatreonInfo(PatreonInfoBean pib) {
        return patreonInfoRepository.putPatreonInfo(pib);
    }



}
