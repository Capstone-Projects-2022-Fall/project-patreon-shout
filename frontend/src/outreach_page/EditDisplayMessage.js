import reactStringReplace from "react-string-replace";

/**
 * Edits a given message to fit the PatreonShout standards of displaying messages
 *
 * @param givenMessage raw message typed into a text field
 * @param showLink True if we should replace {link}, false if {link} should simply be removed from the message
 * @param hexColor HEX color value, including the # symbol
 * @returns Edited message object
 * @constructor
 */
function EditDisplayMessage(givenMessage, showLink, hexColor) {
    const exampleMessage = "This is example text for a Patreon post!  When you create a new Patreon post, all of its text will be put here."
    const exampleLink = "https://www.patreon.com/posts/example-post-194817310"

    givenMessage = reactStringReplace(givenMessage, /(\\n|<br>|<br\/>)/g, () => <br/>);
    givenMessage = reactStringReplace(givenMessage, '{content}', () => exampleMessage);
    givenMessage = reactStringReplace(givenMessage, '{link}', () => (
        showLink ?
            <a style={{color: hexColor, cursor: "pointer"}}>{exampleLink}</a>
            :
            null));
    return givenMessage;
}

export default EditDisplayMessage;