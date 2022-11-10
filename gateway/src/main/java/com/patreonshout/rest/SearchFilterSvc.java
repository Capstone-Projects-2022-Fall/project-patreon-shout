package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.SearchFilter;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.FilterAddRequest;
import com.patreonshout.beans.request.FilterDeleteRequest;
import com.patreonshout.jpa.SearchFiltersRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.SearchFilterImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SearchFilter RESTful Endpoint Interface
 */
@RestController
public class SearchFilterSvc extends BaseSvc implements SearchFilterImpl {

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private WebAccountFunctions webAccountFunctions;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the search_filters table in the database
     */
    @Autowired
    private SearchFiltersRepository searchFiltersRepository;

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> DeleteFilter(FilterDeleteRequest filterDeleteRequest) {
        SearchFilter sf = searchFiltersRepository.getReferenceById(filterDeleteRequest.getFilterId());

        if(!sf.getWebAccount().getLoginToken().equals(filterDeleteRequest.getLoginToken())) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Specified login token does not match the requested list's user login token.");
        }

//        searchFiltersRepository.delete(sf);
        searchFiltersRepository.deleteSearchFilterByFilterId(filterDeleteRequest.getFilterId());

        return ResponseUtil.Generic(HttpStatus.OK, "Search filter removed.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserFilters(String loginToken) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(loginToken);

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        // build response so ResponseEntity can parse the returned objects correctly
        List<Map<String, String>> response = new ArrayList<>();

        for (SearchFilter sf : userAccount.getSearchFilters()) {
            Map<String, String> listResponse = new HashMap<>();

            listResponse.put("filter_id", sf.getFilterId().toString());
            listResponse.put("filter_name", sf.getFilterName());
            listResponse.put("filter", sf.getFilter());

            response.add(listResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> AddFilter(FilterAddRequest filterAddRequest) throws PSException {
        WebAccount userAccount = webAccountFunctions.getAccount(filterAddRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        SearchFilter sf = new SearchFilter();
        sf.setFilter(filterAddRequest.getFilter());
        sf.setFilterName(filterAddRequest.getFilterName());
        sf.setWebAccount(userAccount);

        searchFiltersRepository.save(sf);

        return ResponseUtil.Generic(HttpStatus.CREATED, "Search filter created.");
    }
}
