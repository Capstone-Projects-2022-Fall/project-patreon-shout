import { config } from "../../../Constants";

/**
 * Creates a new user filter
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */

export async function addFilter(request) {
    return fetch(config.url.API_URL + '/filters', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}