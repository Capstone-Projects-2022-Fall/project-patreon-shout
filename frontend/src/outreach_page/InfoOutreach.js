function InfoOutreach() {
    return (
        <div>
            Information:<br/>
            {"{content}"} = Replaced with the contents of a Patreon post<br/>
            {"{link}"} = Replaced with the link to the Patreon post<br/>
            {"\\n"} = Adds a new line to the message
        </div>
    );
}

export default InfoOutreach;