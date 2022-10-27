/**
 * Creates a new user list
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */

export async function addList(request) {
    return fetch('http://localhost:5000/lists/list', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': 'http://localhost:5000'
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}