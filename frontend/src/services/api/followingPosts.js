import { config } from "../../Constants";

/**
 * Gets the posts from the creators that the user follows
 *
 * @param loginToken is the user's login token
 * @returns {Promise<any>} is the json data returned
 */
export function getFollowingPosts(loginToken) {
    return fetch(config.url.API_URL + '/posts/following?loginToken=' + loginToken, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}