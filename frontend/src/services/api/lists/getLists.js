/**
 * Gets the List objects of the user
 *
 * @param token is the login token of the user's session
 * @returns {Promise<any>} is the json data returned
 */
export async function getLists(token) {
    return fetch('http://localhost:5000/lists/user?loginToken=' + token, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': 'http://localhost:5000'
        }
    })
        .then(data => data.json())
}