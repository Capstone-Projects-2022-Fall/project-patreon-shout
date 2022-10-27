/**
 * Gets the posts of a specific creator
 *
 * @param creator is the creator we want to get the posts of
 * @returns {Promise<any>} is the json data returned
 */
export function getPosts(creator) {
    return fetch('http://localhost:5000/posts/creator?creator=' + creator, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': 'http://localhost:5000'
        }
    })
        .then(data => data.json())
}