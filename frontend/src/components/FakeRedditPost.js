import React from "react";
import "./comp_css/FakeRedditPost.css"
import NotificationsNoneRoundedIcon from '@mui/icons-material/NotificationsNoneRounded';

function FakeRedditPost({subreddit}) {


    return (
        <div className="post-outline">
            <div className="post-container">
                <div className="vote-container">
                    <div className="vote-arrows">
                        <div className="upvote">
                            up
                        </div>
                        <div className="counter">
                            counter
                        </div>
                        <div className="downvote">
                            down
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
                            <NotificationsNoneRoundedIcon />
                        </div>
                    </div>

                    <div className="post-title">
                        <div className="title-content">
                            title
                        </div>
                    </div>

                    <div className="post-description">
                        <div className="post-content">
                            <p className="post-content-p">
                                wowowowowowowowowowo very cool content
                            </p>
                        </div>
                    </div>

                    <div className="post-options">
                        <div className="option-icons">
                            <div className="option-comments">
                                comments
                            </div>
                            <div className="option-share">
                                share
                            </div>
                            <div className="option-approveRemoveSpam">
                                <div className="approve">
                                    approve
                                </div>
                                <div className="remove">
                                    remove
                                </div>
                                <div className="spam">
                                    spam
                                </div>
                            </div>
                            <div className="option-shield">
                                shield icon
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default FakeRedditPost;