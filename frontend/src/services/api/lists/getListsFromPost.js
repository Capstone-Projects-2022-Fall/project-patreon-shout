import { config } from "../../../Constants";

/**
 * Gets the List objects of the user that contain a certain post
 *
 * @returns {Promise<any>} is the json data returned
 * @param url is the url of the post
 * @param token is the login token of the user's session
 */
export async function getListsFromPost(token, url) {
    return fetch(config.url.API_URL + '/lists/post?loginToken=' + token + '&url=' + url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}