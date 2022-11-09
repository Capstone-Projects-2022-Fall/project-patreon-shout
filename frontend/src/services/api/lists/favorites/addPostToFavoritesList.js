import { config } from "../../../../Constants";

/**
 * Adds a post to a list
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */
export async function addPostToFavoritesList(request) {
    return fetch(config.url.API_URL + '/lists/favorite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}