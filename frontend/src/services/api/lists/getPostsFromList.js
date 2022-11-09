import { config } from "../../../Constants";

/**
 * Gets the posts from a list
 *
 * @returns {Promise<any>} is the json data returned
 * @param token is the login token of the user's session
 * @param list_id is the id of the list we are trying to find the posts of
 */
export async function getPostsFromList(token, list_id) {
    return fetch(config.url.API_URL + '/lists/list?loginToken=' + token + '&list_id=' + list_id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}