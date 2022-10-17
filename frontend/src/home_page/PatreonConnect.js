import React from "react";
import useToken from "../useToken";
import Login from "../login_page/Login";

function PatreonConnect() {
    const patreonUrl = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=Vti4zJZ3GT_wYu4uVMh-UdZDBdbjz8xzxlW3ejvtLGvCoh6cZDR30sYv7tNptFF4" +
        "&redirect_uri=http://patreon-shout-gateway.us-east-2.elasticbeanstalk.com/webhook" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const patreonUrlDev = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=Aymjq1-b_ZkmaeejretFStklrikzMGcG-LEtCkt3GvOeiuOO2MY0_hluLoUzC4ce" +
        "&redirect_uri=http://localhost:5000/webhook" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (<div className="PatreonConnect">

        <a href={patreonUrlDev + token}>
            Connect with Patreon
        </a>
    </div>);
}

export default PatreonConnect;