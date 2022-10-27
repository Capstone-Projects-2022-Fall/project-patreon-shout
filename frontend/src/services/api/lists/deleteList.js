/**
 * Deletes a user list
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */

export async function deleteList(request) {
    console.log(JSON.stringify(request));
    return fetch('http://localhost:5000/lists/list', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': 'http://localhost:5000'
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}