import React from "react";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Button} from "@mui/material";
import {config} from "../Constants";

function RedditConnect() {
    const { token, setToken } = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    const redditUrl = "https://www.reddit.com/api/v1/authorize" +
        "?response_type=code" +
        "&client_id=HzeEs2Uwadhr9_1vF2nf5w" +
        "&redirect_uri=" + config.url.API_URL + "/receivers/reddit/oauth" +
        "&scope=submit" +
        "&state=" + token +
        "&duration=permanent";

    const redditUrlTest = "https://www.reddit.com/api/v1/authorize" +
        "?response_type=code" +
        "&client_id=HzeEs2Uwadhr9_1vF2nf5w" +
        "&redirect_uri=https://webhook.site/218cbcbd-3c4e-4ec5-91d2-a5e29d325d9b" +
        "&scope=submit" +
        "&state=" + token +
        "&duration=permanent";


    return (
        <div className="PatreonConnect">
            <Button target="_blank" href={redditUrl} sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
                Connect To Reddit
            </Button>
        </div>);
}

export default RedditConnect;