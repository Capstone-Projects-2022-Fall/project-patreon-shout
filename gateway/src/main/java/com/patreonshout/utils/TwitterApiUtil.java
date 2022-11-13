package com.patreonshout.utils;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.patreonshout.PSException;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.config.credentials.TwitterCredentials;
import com.patreonshout.jpa.WebAccountRepository;
import com.twitter.clientlib.ApiClientCallback;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetCreateRequest;
import com.twitter.clientlib.model.TweetCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * This class is used to send new posts to
 */
@Component
public class TwitterApiUtil {

    /**
     * An autowired Spring component that will give the twitter credentials for our Twitter app client
     */
    @Autowired
    TwitterCredentials twitterCredentials;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    WebAccountRepository webAccountRepository;

    /**
     * client is the twitter client we will use to talk to the Twitter API
     */
    private TwitterApi client;

    /**
     * account is the {@link com.patreonshout.beans.WebAccount} object associated with a user
     */
    private WebAccount account;

    /**
     * Initializes the Twitter Api client we use to talk to Twitter's API
     *
     * @param account is the {@link com.patreonshout.beans.WebAccount} object associated with a user
     * @param userAccessToken is the content creator's access token
     * @param userRefreshToken is the content creator's refresh token
     */
    public void setClient(WebAccount account, String userAccessToken, String userRefreshToken) throws PSException {
        try {
            this.client = new TwitterApi(new TwitterCredentialsOAuth2(twitterCredentials.getClientID(), twitterCredentials.getClientSecret(), userAccessToken, userRefreshToken, true));
            this.client.addCallback(new MaintainUserTokens());
        }
        catch (Exception e) {
            throw new PSException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to establish Twitter API connection.");
        }

        this.account = account;
        try {
            this.client.refreshToken();
        }
        catch (Exception e) {
            throw new PSException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while trying to refresh existing token : " + e);
        }
    }

    /**
     * Will send a tweet on behalf of a user
     *
     * @param text is the text within the tweet we want to send
     */
    public void sendTweet(String text) {
        TweetCreateRequest request = new TweetCreateRequest();
        request.setText(text);

        try {
            TweetCreateResponse result = client.tweets().createTweet(request).execute();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TweetsApi#createTweet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }


    /**
     * Class that will refresh the twitter access token and refresh token
     */
    private class MaintainUserTokens implements ApiClientCallback {

        /**
         * refreshes the user's twitter access token and refresh token
         *
         * @param userTokens are the tokens that will replace the current access and refresh tokens for the user
         */
        @Override
        public void onAfterRefreshToken(OAuth2AccessToken userTokens) {
            SocialIntegration socialIntegration = account.getSocialIntegration();

            socialIntegration.setTwitterAccessToken(userTokens.getAccessToken());
            socialIntegration.setTwitterRefreshToken(userTokens.getRefreshToken());

            account.setSocialIntegration(socialIntegration);

            webAccountRepository.save(account);
        }
    }
}
