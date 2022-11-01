import { config } from "../../Constants";

/**
 * Gets the posts of a specific creator
 *
 * @param creator is the creator we want to get the posts of
 * @returns {Promise<any>} is the json data returned
 */
export function getPosts(creator) {
    return fetch(config.url.API_URL + '/posts/creator?creator=' + creator, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}