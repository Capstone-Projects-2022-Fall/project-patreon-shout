import reactStringReplace from "react-string-replace";

function EditDisplayMessage(givenMessage, showLink) {
    const exampleMessage = "This is example text for a Patreon post!  When you create a new Patreon post, all of its text will be put here."
    const exampleLink = "https://www.patreon.com/posts/example-post-194817310"

    givenMessage = reactStringReplace(givenMessage, /(\\n|<br>|<br\/>)/g, () => <br/>);
    givenMessage = reactStringReplace(givenMessage, '{content}', () => exampleMessage);
    givenMessage = reactStringReplace(givenMessage, '{link}', () => (
        showLink ?
            <a style={{color: "#0000EE", cursor: "pointer"}}>{exampleLink}</a>
            :
            null));
    return givenMessage;
}

export default EditDisplayMessage;