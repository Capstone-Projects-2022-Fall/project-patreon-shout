/**
 * Deletes the local session token from the database
 *
 * @param token is the session token
 * @returns {Promise<any>} is the json data returned
 */
export function logoutUser(token) {
    return fetch('http://localhost:5000/webaccount/logout?login_token=' + token, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Origin': 'http://localhost:5000'
        }
    })
        .then(data => data.json())
}