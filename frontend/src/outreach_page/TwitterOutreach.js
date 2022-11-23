import React from "react";
import TwitterConnect from "../components/TwitterConnect";

import './outreach_css/Outreach.css';

/**
 * The twitter social platform cross-posting component
 *
 * @returns Interface for the user to cross post to twitter with custom post details
 */
function TwitterOutreach() {


    return (
        <div className="outreachSettings">
            <TwitterConnect />
        </div>
    );
}

export default TwitterOutreach;