package com.patreonshout.utils;

import com.patreonshout.PSException;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetCreateRequest;
import com.twitter.clientlib.model.TweetCreateResponse;
import org.springframework.http.HttpStatus;

/**
 * This class is used to send new patreon posts to twitter
 */
public class TwitterApiUtil {

    /**
     * client is what we use to talk to the Twitter API
     */
    TwitterApi client;

    /**
     * Initializes the Twitter Api client we use to talk to Twitter's API then sends a message
     *
     * @param clientId is our Twitter app's client id
     * @param clientSecret is our Twitter app's client secret
     * @param userAccessToken is the user's Twitter access token
     * @param userRefreshToken is the user's Twitter refresh token
     * @param text is the body we want to send in the tweet
     * @throws PSException in case we have internal server errors
     */
    public void sendTweet(String clientId, String clientSecret, String userAccessToken, String userRefreshToken, String text) throws PSException {

        try {
            client = new TwitterApi(new TwitterCredentialsOAuth2(clientId, clientSecret, userAccessToken, userRefreshToken));
        }
        catch (Exception e) {
            throw new PSException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to establish Twitter API connection.");
        }

        send(text);
    }

    public void send(String text) {
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

    // TODO: use a different way to refresh the user's tokens
//    /**
//     * Class that will refresh the twitter access token and refresh token
//     */
//    private class MaintainUserTokens implements ApiClientCallback {
//
//        /**
//         * refreshes the user's twitter access token and refresh token
//         *
//         * @param userTokens are the tokens that will replace the current access and refresh tokens for the user
//         */
//        @Override
//        public void onAfterRefreshToken(OAuth2AccessToken userTokens) {
//            SocialIntegration socialIntegration = account.getSocialIntegration();
//
//            socialIntegration.setTwitterAccessToken(userTokens.getAccessToken());
//            socialIntegration.setTwitterRefreshToken(userTokens.getRefreshToken());
//
//            account.setSocialIntegration(socialIntegration);
//
//            webAccountRepository.save(account);
//        }
//    }
}
