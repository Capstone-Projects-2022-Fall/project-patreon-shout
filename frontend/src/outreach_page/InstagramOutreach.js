import './outreach_css/Outreach.css';
import ConnectIntegrationButton from "../settings/ConnectIntegrationButton";
import useToken from "../services/useToken";
import Login from "../login_page/LoginPage";
import {Alert, Slider} from "@mui/material";
import FakeInstagramPost from "../settings/FakeInstagramPost";
import TextField from "@mui/material/TextField";
import React, {useEffect, useState} from "react";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import {config} from "../Constants";
import {getBlurredImage} from "../services/api/image/getBlurredImage";
import Typography from "@mui/material/Typography";
import {MuiColorInput} from "mui-color-input";
import useDebounce from "./UseDebounce";
import {putInstagramPostDetails} from "../services/api/webaccount/putInstagramPostDetails";
import FakeRedditPost from "../components/FakeRedditPost";

/**
 * The instagram social platform cross-posting component
 *
 * @returns Interface for the user to cross post to instagram with custom post details
 */
function InstagramOutreach({publicMessage, privateMessage, storedImageUrl, blurAmount, textColor}) {
    const [isProcessing, setIsProcessing] = useState(false);
    const [publicTextField, setPublicTextField] = useState(publicMessage);
    const [privateTextField, setPrivateTextField] = useState(privateMessage);
    const [imageTextField, setImageTextField] = useState(storedImageUrl);
    const [displayedImageTextField, setDisplayedImageTextField] = useState(imageTextField);
    const {token, setToken} = useToken();

    // Image
    const [blurAmountValueActual, setBlurAmountValueActual] = useState(blurAmount);
    const [textColorValueActual, setTextColorValueActual] = useState(textColor);
    // This debounced var will ignore all changes on textColor until 100ms has passed, then execute it will change its own value
    const debouncedTextColor = useDebounce(textColorValueActual, 100)

    // Settings
    const patreonPostTitle = "This is an example Patreon post title! When you make a Patreon post, the title will be placed here.";


    useEffect(() => {
        console.log("Called!");

        setIsProcessing(true);

        const getImage = async () => {
            // Replace # with %23 as we need to change all special characters to its HEX value representation
            await getBlurredImage(token, imageTextField, blurAmountValueActual, patreonPostTitle, textColorValueActual.replace('#', '%23'))
                .then(imageBlob => {
                    // Create a local URL for that image and assign it
                    setDisplayedImageTextField(URL.createObjectURL(imageBlob));
                });
        }

        getImage().catch(console.error);

        setIsProcessing(false);
    }, [blurAmountValueActual, debouncedTextColor])

    if (!token) { // TODO: check if session token is also valid via http request to database
        return <Login setToken={setToken}/>
    }

    return (
        <div className="outreachSettings">
            <Alert severity="warning" sx={{width: "100%"}}>Instagram functions <b>only for certified PatreonShout
                developers</b>!</Alert>
            <Alert severity="info" sx={{width: "100%"}}>
                Instagram account <b>must</b> be a&nbsp;
                <a href="https://help.instagram.com/502981923235522">business</a> account. <br/>

                Facebook account <b>must</b> contain a&nbsp;
                <a href="https://www.facebook.com/pages/?category=your_pages">Page</a> and&nbsp;
                <a href="https://help.instagram.com/399237934150902">connected with your Instagram Business account</a>!
            </Alert>

            {/* Disconnect Integration Button: https://www.facebook.com/settings?tab=business_tools&section=active */}
            <ConnectIntegrationButton
                button_name="Connect Facebook Page"
                integration_url="https://www.facebook.com/dialog/oauth"
                client_id="824898048795218"
                redirect_uri={config.url.API_URL + "/receivers/instagram/oauth"}
                response_type="code"
                scope="public_profile,instagram_basic,pages_read_engagement,pages_show_list,instagram_content_publish,catalog_management,instagram_shopping_tag_products"
                state={token}
            />

            <TextField
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Public"
                multiline
                rows={3}
                value={publicTextField}
                onChange={(e) => setPublicTextField(e.target.value)}
            />

            <TextField
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Private"
                multiline
                rows={3}
                value={privateTextField}
                onChange={(e) => setPrivateTextField(e.target.value)}
            />

            {/* Image-related fields */}
            <TextField
                className="image-url-textfield"
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Image URL"
                multiline
                value={imageTextField}
                onChange={(e) => setImageTextField(e.target.value)}
                disabled={isProcessing}
            />

            <div className="image-settings-container">
                <div className="radius-slider-container">
                    <Typography gutterBottom>Blur Amount %</Typography>
                    <Slider
                        className="radius-slider"
                        min={1}
                        aria-label="Always visible"
                        defaultValue={blurAmountValueActual}
                        valueLabelDisplay="on"
                        onChangeCommitted={(_, v) => setBlurAmountValueActual(v)}
                        disabled={isProcessing}
                    />
                </div>

                <div className="color-picker-container">
                    <MuiColorInput
                        value={textColorValueActual}
                        isAlphaHidden={true}
                        onChange={(value, colors) => setTextColorValueActual(colors.hex)}
                        disabled={isProcessing}
                    />
                </div>

                <div className="save-button-container">
                    <NewSaveButton
                        givenFunc={[putSocialIntegrationMessages, putInstagramPostDetails]}
                        funcArgs={[
                            {
                                login_token: token,
                                integration_name: "INSTAGRAM",
                                public_message: publicTextField,
                                private_message: privateTextField
                            },
                            {
                                login_token: token,
                                instagram_image_url: imageTextField,
                                instagram_blur_amount: blurAmountValueActual,
                                instagram_message_color: textColorValueActual
                            }
                        ]}
                    />
                </div>
            </div>

            <div className="socialPlatformContainerInstagram">
                <div className="socialPlatformContainerItemInstagram">
                    <FakeInstagramPost
                        displayMessage={publicTextField}
                        imageUrl={displayedImageTextField}
                    />
                </div>
                <div className="socialPlatformContainerItemInstagram">
                    <FakeInstagramPost
                        displayMessage={privateTextField}
                        imageUrl={displayedImageTextField}
                    />
                </div>
            </div>
        </div>
    );
}

export default InstagramOutreach;