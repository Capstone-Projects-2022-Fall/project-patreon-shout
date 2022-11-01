import { config } from "../../../Constants";

/**
 * Updates a user list
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */
export async function updateList(request) {
    return fetch(config.url.API_URL + '/lists/list', {
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