import React from "react";
import './outreach_css/Outreach.css';
import ConnectIntegrationButton from "../settings/ConnectIntegrationButton";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Alert} from "@mui/material";
import FakeInstagramPost from "../settings/FakeInstagramPost";


/**
 * The instagram social platform cross-posting component
 *
 * @returns Interface for the user to cross post to instagram with custom post details
 */
function InstagramOutreach() {
    const {token, setToken} = useToken();

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (
        <div className="outreachSettings">
            <Alert severity="warning" sx={{width: "100%"}}>Instagram functions <b>only for certified PatreonShout developers</b>!</Alert>
            <Alert severity="info" sx={{width: "100%"}}>
                Instagram account <b>must</b> be a <a href="https://help.instagram.com/502981923235522">business</a> account. <br/>
                Facebook account <b>must</b> contain a <a href="https://www.facebook.com/pages/?category=your_pages">Page</a> and <a href="https://help.instagram.com/399237934150902">connected with your Instagram Business account</a>!
            </Alert>

            <ConnectIntegrationButton
                button_name="Connect Facebook Page"
                integration_url="https://www.facebook.com/dialog/oauth"
                client_id="824898048795218"
                redirect_uri="https://webhook.site/cd1829bb-80f9-42eb-b298-2c70577ec6c2"
                // redirect_uri="https://ayser.backend.outofstonk.com:5000/receivers/instagram/oauth"
                response_type="code"
                scope="public_profile,instagram_basic,pages_read_engagement,pages_show_list,instagram_content_publish,catalog_management,instagram_shopping_tag_products"
                state={token}
            />

            {/* Disconnect Integration Button: https://www.facebook.com/settings?tab=business_tools&section=active */}

            <FakeInstagramPost/>
        </div>
    );
}

export default InstagramOutreach;