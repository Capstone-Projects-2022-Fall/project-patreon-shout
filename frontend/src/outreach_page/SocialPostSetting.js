import React from "react";
import './outreach_css/SocialPostSetting.css';


/**
 * SocialPostSetting is the box that will hold all the information for a
 *
 * @returns a social platform post settings interface
 */
function SocialPostSetting({children, platform}) {


    return (
        <div className="settingsContent">
            {platform}
            {children}
        </div>
    );

}

export default SocialPostSetting;