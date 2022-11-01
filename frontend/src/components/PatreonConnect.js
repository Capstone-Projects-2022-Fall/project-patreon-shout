import React from "react";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Button} from "@mui/material";

function PatreonConnect() {
    const patreonUrl = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=Vti4zJZ3GT_wYu4uVMh-UdZDBdbjz8xzxlW3ejvtLGvCoh6cZDR30sYv7tNptFF4" +
        "&redirect_uri=http://patreon-shout-gateway.us-east-2.elasticbeanstalk.com/webhook" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const patreonUrlDev = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=QshCpqHpThL5sckRQX88TwE9vPr4pEWOjdyZbAKVIJie1hhOdPbsp6Qaqrd9C7M6" +
        "&redirect_uri=http://localhost:5000/webhook" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (<div className="PatreonConnect">

        <Button target="_blank" href={patreonUrlDev + token} sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
            Oauth
        </Button>
    </div>);
}

export default PatreonConnect;