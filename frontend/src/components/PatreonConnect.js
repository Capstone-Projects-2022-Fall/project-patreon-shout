import React from "react";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Button} from "@mui/material";
import {config} from "../Constants";

/**
 * This is the Patreon Connect function which will be used for patreon connect oauth button in the Creator settings
 *
 * @returns Connect to Patreon Button
 * @constructor
 */
function PatreonConnect() {
    const patreonUrl = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=Vti4zJZ3GT_wYu4uVMh-UdZDBdbjz8xzxlW3ejvtLGvCoh6cZDR30sYv7tNptFF4" +
        "&redirect_uri=" + config.url.API_URL + "/receivers/patreon/oauth" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const patreonUrlDev = "https://www.patreon.com/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=Aymjq1-b_ZkmaeejretFStklrikzMGcG-LEtCkt3GvOeiuOO2MY0_hluLoUzC4ce" +
        "&redirect_uri=" + config.url.API_URL + "/receivers/patreon/oauth" +
        "&scope=identity identity.memberships campaigns w:campaigns.webhook campaigns.members campaigns.members.address campaigns.posts" +
        "&state=";

    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (
        <div className="PatreonConnect">
            <Button target="_blank" href={patreonUrl + token} sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
                Connect To Patreon
        </Button>
    </div>);
}

export default PatreonConnect;