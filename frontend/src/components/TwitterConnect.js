import React from "react";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Button} from "@mui/material";

function TwitterConnect() {
    // const patreonUrl = "https://www.patreon.com/oauth2/authorize" +
    //     "?response_type=code" +
    //     "&client_id=Vti4zJZ3GT_wYu4uVMh-UdZDBdbjz8xzxlW3ejvtLGvCoh6cZDR30sYv7tNptFF4" +
    //     "&redirect_uri=http://patreon-shout-gateway.us-east-2.elasticbeanstalk.com/receivers/patreon/oauth" +
    //     "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
    //     "&state=";

    const twitterUrlDev = "https://twitter.com/i/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=NWhrMFdaeE9Bd3diVXlTY19oYUg6MTpjaQ" +
        "&redirect_uri=https%3A%2F%2Fwebhook.site%2Fe1a17e9a-8542-49c8-a48b-c24d1707e7a0 " +
        "&scope=tweet.read%20users.read%20follows.read%20follows.write%20offline.access" +
        "&state=state" +
        "&code_challenge=challenge" +
        "&code_challenge_method=plain";

    const something = "https://twitter.com/i/oauth2/authorize?response_type=code&client_id=cFo1S1g4azUweVJzWFBBcEx1LVE6MTpjaQ&redirect_uri=https%3A%2F%2Fvotacionya.000webhostapp.com%2Fcallback%2Flogintwitter.php &scope=tweet.read%20users.read%20follows.read+follows.write%20offline.access&state=state&code_challenge=NWRFR2hCRWU%3D&code_challenge_method=plain";

    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (
        <div className="PatreonConnect">
            <Button target="_blank" href={twitterUrlDev} sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
                Connect To Twitter
            </Button>
        </div>);
}

export default TwitterConnect;