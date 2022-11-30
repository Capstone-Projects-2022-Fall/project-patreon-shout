import './outreach_css/InfoOutreach.css';

function InfoOutreach() {
    return (
        <h2 className='information'>
            Information:<br/>
            {"{content}"} = Replaced with the contents of a Patreon post<br/>
            {"{link}"} = Replaced with the link to the Patreon post<br/>
            {"\\n"} = Adds a new line to the message
        </h2>
    );
}

export default InfoOutreach;