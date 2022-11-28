import React from "react";
import "./comp_css/FakeRedditPost.css"
import reactStringReplace from "react-string-replace";

function FakeRedditPost({postTitle, subreddit, textField}) {

    // Constants
    const publicMessageExample = "This is example text for a public post!  When you create a new Patreon post and mark " +
        "it as public, all of its text will be put here."
    const emptyMessage = "Empty message!";


    const editDisplayMessage = (givenMessage) => {
        givenMessage = reactStringReplace(givenMessage, /(\\n|<br>|<br\/>)/g, () => <br/>);
        givenMessage = reactStringReplace(givenMessage, '{content}', () => publicMessageExample);
        return givenMessage;
    }

    // Raw message containers
    let displayMessage = (textField && textField.length > 0) ? textField : emptyMessage;

    // Edited message containers
    let editedDisplayMessage = editDisplayMessage(displayMessage);

    return (
        <div className="post-outline">
            <div className="post-container">
                <div className="vote-container">
                    <div className="vote-arrows">
                        <div className="upvote">
                            <i className="icon icon-upvote_fill"/>
                        </div>
                        <div className="counter">
                            1
                        </div>
                        <div className="downvote">
                            <i className="icon icon-downvote"/>
                        </div>
                    </div>
                </div>

                <div className="content-container">
                    <div className="subreddit-and-user">
                        <div className="subreddit-and-user-content">
                            <div className="generic-subreddit-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" className="subreddit-icon">
                                    <path
                                        d="M16.5,2.924,11.264,15.551H9.91L15.461,2.139h.074a9.721,9.721,0,1,0,.967.785ZM8.475,8.435a1.635,1.635,0,0,0-.233.868v4.2H6.629V6.2H8.174v.93h.041a2.927,2.927,0,0,1,1.008-.745,3.384,3.384,0,0,1,1.453-.294,3.244,3.244,0,0,1,.7.068,1.931,1.931,0,0,1,.458.151l-.656,1.558a2.174,2.174,0,0,0-1.067-.246,2.159,2.159,0,0,0-.981.215A1.59,1.59,0,0,0,8.475,8.435Z"></path>
                                </svg>
                            </div>
                            <div className="content-subreddit-name underline">
                                r/{subreddit}
                            </div>
                            <div className="content-separator">
                                ∙
                            </div>
                            <div className="content-username">
                                Posted by&thinsp;
                            </div>
                            <div className="content-username underline">
                                u/username
                            </div>
                            <div className="content-time-ago">
                                2&thinsp;hours ago
                            </div>
                        </div>
                        <div className="bell-icon">
                            <i className="icon icon-notification"/>
                        </div>
                    </div>

                    <div className="post-title">
                        <div className="title-content">
                            {postTitle}
                        </div>
                    </div>

                    <div className="post-description">
                        <div className="post-content">
                            <p>
                                {editedDisplayMessage}
                            </p>
                        </div>
                    </div>

                    <div className="post-options">
                        <div className="icons option-comments">
                            <i className="icon icon-comment" />
                            0
                        </div>
                        <div className="icons option-share">
                            <i className="icon icon-share"/>
                            Share
                        </div>
                        <div className="option-approveRemoveSpam">
                            <div className="icons approve icon-desktop">
                                <i className="icon icon-approve"/>
                                Approve
                            </div>
                            <div className="icons remove icon-desktop">
                                <i className="icon icon-remove"/>
                                Remove
                            </div>
                            <div className="icons spam icon-desktop">
                                <i className="icon icon-spam"/>
                                Spam
                            </div>

                            {/* mobile */}
                            <div className="icons approve icon-mobile">
                                <i className="icon icon-approve"/>
                            </div>
                            <div className="icons remove icon-mobile">
                                <i className="icon icon-remove"/>
                            </div>
                            <div className="icons spam icon-mobile">
                                <i className="icon icon-spam"/>
                            </div>
                        </div>
                        <div className="icons option-shield">
                            <i className="icon icon-mod"/>
                        </div>
                        <div className="icons option-more">
                            <i className="icon icon-overflow_horizontal"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default FakeRedditPost;