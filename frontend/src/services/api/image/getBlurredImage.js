import {config} from "../../../Constants";

/**
 * Gets the login token's social integrations
 *
 * @param token login token of the user's session
 * @param imageUrl image url of the desired picture
 * @param radius amount of gaussian blur (1 to 100)
 * @param text message to display above the filtered image
 * @param textColor color of the message
 * @returns {Promise<any>} is the json data returned
 */
export async function getBlurredImage(token, imageUrl, radius, text, textColor) {
    return fetch(config.url.API_URL + '/images/blur' +
        '?login_token=' + token +
        '&image_url=' + imageUrl +
        '&radius=' + radius +
        '&text=' + text +
        '&text_color=' + textColor, {
        method: 'GET',
        headers: {
            'Content-Type': 'image/png',
            'Origin': config.url.API_URL
        }
    })
        .then(response => response.blob());
}