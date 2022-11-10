import { config } from "../../Constants";

/**
 * Gets the posts of a specific creator
 *
 * @param campaign is the campaign ID we want to get the posts of
 * @returns {Promise<any>} is the json data returned
 */
export function getPosts(campaign) {
    return fetch(config.url.API_URL + '/posts/creator?campaign=' + campaign, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}