import React from "react";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Button} from "@mui/material";

/**
 * This is the TwitterConnect function which will be used to connect to twitter for cross-posting
 *
 * @returns Connect to twitter button
 * @constructor
 */
function TwitterConnect() {
    // const patreonUrl = "https://www.patreon.com/oauth2/authorize" +
    //     "?response_type=code" +
    //     "&client_id=Vti4zJZ3GT_wYu4uVMh-UdZDBdbjz8xzxlW3ejvtLGvCoh6cZDR30sYv7tNptFF4" +
    //     "&redirect_uri=http://patreon-shout-gateway.us-east-2.elasticbeanstalk.com/receivers/patreon/oauth" +
    //     "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
    //     "&state=";

    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    const twitterUrlDev = "https://twitter.com/i/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=NWhrMFdaeE9Bd3diVXlTY19oYUg6MTpjaQ" +
        "&redirect_uri=http://127.0.0.1:5000/receivers/twitter/oauth " +
        "&scope=tweet.read%20tweet.write%20users.read%20offline.access" +
        "&state=" + token +
        "&code_challenge=challenge" +
        "&code_challenge_method=plain";

    const twitterUrl = "https://twitter.com/i/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=NWhrMFdaeE9Bd3diVXlTY19oYUg6MTpjaQ" +
        "&redirect_uri=https://prodprod.backend.outofstonk.com/receivers/twitter/oauth " +
        "&scope=tweet.read%20tweet.write%20users.read%20offline.access" +
        "&state=" + token +
        "&code_challenge=challenge" +
        "&code_challenge_method=plain";



    return (
        <div className="PatreonConnect">
            <Button target="_blank" href={twitterUrl} sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
                Connect To Twitter
            </Button>
        </div>);
}

export default TwitterConnect;