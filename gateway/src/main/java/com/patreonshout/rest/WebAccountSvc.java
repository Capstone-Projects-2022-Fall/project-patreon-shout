package com.patreonshout.rest;

import com.patreonshout.beans.IntegrationRequestBean;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.jpa.WebAccount;
import com.patreonshout.rest.interfaces.WebAccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Web Account  RESTful Endpoint Interface
 *
 * <p>
 * Responsibilities:
 * 1) Add items to our webaccounts table in our database
 * 2) Direct communication between the frontend portion of our application with the backend
 * </p>
 */
@RestController
public class WebAccountSvc extends GeneralSvc implements WebAccountImpl {

	/**
	 * webAccount is the wrapper class for {@link com.patreonshout.jpa.WebAccountRepository}
	 */
	@Autowired
	WebAccount webAccount;

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus Register( // This String object will contain an error message, or be empty if no error occurred
	                            @RequestBody RegisterRequest registerRequest
	) {
		// TODO: Ensure username and password are sanitized and fit specific requirements
		webAccount.putAccount(registerRequest);

		return HttpStatus.CREATED; // Http 201
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus Integration(
			@RequestBody IntegrationRequestBean integrationRequestBean
	) {
		webAccount.putIntegration(integrationRequestBean.getWebaccount().getWebaccount_id(),
				integrationRequestBean.getIntegrationType(),
				integrationRequestBean.getData());
		return HttpStatus.OK; // Http 200
	}
}