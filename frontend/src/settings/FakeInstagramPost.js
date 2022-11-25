import './setting_css/FakeInstagramPost.css'
import reactStringReplace from "react-string-replace";

function FakeInstagramPost() {
    // Constants
    const publicMessageExample = "This is example text for a public post!  When you create a new Patreon post and mark " +
        "it as public, all of its text will be put here."
    const emptyMessage = "Empty message!";

    // Dynamics
    let profileName = "patreonshout"; // Maybe dynamic...
    let descriptionAndCaptions = "Today and every day, we have so much to be grateful for.\n" +
        "\n" +
        "Happy Thanksgiving to our service members and military families whose loved ones are on duty and unable to be with their families. We hope your day is filled with joy and gratitude.";
    let profilePicUrl = "https://zeoob.com/assets/img/default-img.png"
    let postPicUrl = "https://i.imgur.com/rBX2KuR.jpg";


    const editDisplayMessage = (givenMessage) => {
        givenMessage = reactStringReplace(givenMessage, '<br>', (match, i) => (
            <br/>
        ));
        givenMessage = reactStringReplace(givenMessage, '<br\/>', (match, i) => (
            <br/>
        ));
        givenMessage = reactStringReplace(givenMessage, '{content}', (match, i) => (
            publicMessageExample
        ));
        return givenMessage;
    }


    return (
        <div className="instagram-post-container width100">
            {/* Instagram post header */}
            <div className="post-header width100">
                <header className="profile-details-container">
                    <div className="profile-pic">
                        <img
                            className="profile-pic-image circular"
                            alt="Fake Instagram post"
                            width="32px"
                            height="32px"
                            src={profilePicUrl}
                        />

                        {/*<img*/}
                        {/*    className="profile-story-image"*/}
                        {/*    alt="Fake Instagram post background"*/}
                        {/*    width="100%"*/}
                        {/*    height="100%"*/}
                        {/*    src="https://zeoob.com/assets/img/insta-profile-circle.png"*/}
                        {/*/>*/}
                    </div>
                    <div className="profile-information-container">
                        <div className="profile-information">
                            <div className="profile-name">
                                {profileName}
                            </div>
                            <div className="profile-follow-container">
                                <div className="profile-follow-dot-container">
                                    <span className="profile-follow-dot">•</span>
                                </div>
                                <button className="profile-follow-button">
                                    <div className="profile-follow-button-text-container">
                                        <div className="profile-follow-button-text">
                                            Follow
                                        </div>
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>
                </header>
                <div className="options-button-container">
                    <button className="options-button">
                        <div className="options-button-internal">
                            <div className="options-button-svg-container">
                                <svg aria-label="More options" className="options-button-svg"
                                     color="#262626" fill="#262626" height="24" role="img"
                                     viewBox="0 0 24 24" width="24">
                                    <circle cx="12" cy="12" r="1.5"/>
                                    <circle cx="6" cy="12" r="1.5"/>
                                    <circle cx="18" cy="12" r="1.5"/>
                                </svg>
                            </div>
                        </div>
                    </button>
                </div>
            </div>

            {/* Instagram post picture */}
            <div className="post-image-primary-container">
                <div role="button" className="post-image-button-container">
                    <div className="post-image-button-internal">
                        <div className="post-image-button-internal-picture-container">
                            <img className="post-image-button-internal-picture"
                                 crossOrigin="anonymous"
                                 src={postPicUrl}
                                 alt="Example picture"/>
                        </div>
                    </div>
                </div>
            </div>

            {/* Instagram post text and caption */}
            <div className="text-caption-container">
                <div className="text-caption-internal-container">
                    <div className="text-caption-internal-container-final">

                        {/* Buttons section */}
                        <section className="section-primary-container">
                            {/* Like button */}
                            <span className="heart-container">
                                <button className="heart-button" type="button">
                                    <div className="heart-internal-light">
                                        <svg aria-label="Like" className="light-heart" color="#8e8e8e" fill="#8e8e8e"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <path
                                                d="M16.792 3.904A4.989 4.989 0 0 1 21.5 9.122c0 3.072-2.652 4.959-5.197 7.222-2.512 2.243-3.865 3.469-4.303 3.752-.477-.309-2.143-1.823-4.303-3.752C5.141 14.072 2.5 12.167 2.5 9.122a4.989 4.989 0 0 1 4.708-5.218 4.21 4.21 0 0 1 3.675 1.941c.84 1.175.98 1.763 1.12 1.763s.278-.588 1.11-1.766a4.17 4.17 0 0 1 3.679-1.938m0-2a6.04 6.04 0 0 0-4.797 2.127 6.052 6.052 0 0 0-4.787-2.127A6.985 6.985 0 0 0 .5 9.122c0 3.61 2.55 5.827 5.015 7.97.283.246.569.494.853.747l1.027.918a44.998 44.998 0 0 0 3.518 3.018 2 2 0 0 0 2.174 0 45.263 45.263 0 0 0 3.626-3.115l.922-.824c.293-.26.59-.519.885-.774 2.334-2.025 4.98-4.32 4.98-7.94a6.985 6.985 0 0 0-6.708-7.218Z"/>
                                        </svg>
                                    </div>
                                    <div className="heart-internal-dark">
                                        <span>
                                            <svg aria-label="Like" className="dark-heart" color="#262626" fill="#262626"
                                                 height="24" role="img" viewBox="0 0 24 24" width="24">
                                                <path
                                                    d="M16.792 3.904A4.989 4.989 0 0 1 21.5 9.122c0 3.072-2.652 4.959-5.197 7.222-2.512 2.243-3.865 3.469-4.303 3.752-.477-.309-2.143-1.823-4.303-3.752C5.141 14.072 2.5 12.167 2.5 9.122a4.989 4.989 0 0 1 4.708-5.218 4.21 4.21 0 0 1 3.675 1.941c.84 1.175.98 1.763 1.12 1.763s.278-.588 1.11-1.766a4.17 4.17 0 0 1 3.679-1.938m0-2a6.04 6.04 0 0 0-4.797 2.127 6.052 6.052 0 0 0-4.787-2.127A6.985 6.985 0 0 0 .5 9.122c0 3.61 2.55 5.827 5.015 7.97.283.246.569.494.853.747l1.027.918a44.998 44.998 0 0 0 3.518 3.018 2 2 0 0 0 2.174 0 45.263 45.263 0 0 0 3.626-3.115l.922-.824c.293-.26.59-.519.885-.774 2.334-2.025 4.98-4.32 4.98-7.94a6.985 6.985 0 0 0-6.708-7.218Z"/>
                                            </svg>
                                        </span>
                                    </div>
                                </button>
                            </span>

                            {/* Comments button */}
                            <span className="comments-container">
                                <button className="comments-button" type="button">
                                    <div className="comments-internal-light">
                                        <svg aria-label="Comment" className="light-comments" color="#8e8e8e"
                                             fill="#8e8e8e"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <path
                                                d="M20.656 17.008a9.993 9.993 0 1 0-3.59 3.615L22 22Z" fill="none"
                                                stroke="currentColor" strokeLinejoin="round" strokeWidth="2"/>
                                        </svg>
                                    </div>
                                    <div className="comments-internal-dark">
                                        <svg aria-label="Comment" className="dark-comments" color="#262626"
                                             fill="#262626"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <path
                                                d="M20.656 17.008a9.993 9.993 0 1 0-3.59 3.615L22 22Z" fill="none"
                                                stroke="currentColor" strokeLinejoin="round" strokeWidth="2"/>
                                        </svg>
                                    </div>
                                </button>
                            </span>

                            {/* Share button */}
                            <span className="share-container">
                                <button className="share-button" type="button">
                                    <div className="share-internal-light">
                                        <svg aria-label="Share Post" className="light-share" color="#8e8e8e"
                                             fill="#8e8e8e" height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <line fill="none" stroke="currentColor" strokeLinejoin="round"
                                                  strokeWidth="2" x1="22" x2="9.218"
                                                  y1="3" y2="10.083"/>
                                            <polygon fill="none"
                                                     points="11.698 20.334 22 3.001 2 3.001 9.218 10.084 11.698 20.334"
                                                     stroke="currentColor" strokeLinejoin="round"
                                                     strokeWidth="2"/>
                                        </svg>
                                    </div>
                                    <div className="share-internal-dark">
                                        <svg aria-label="Share Post" className="dark-share" color="#262626"
                                             fill="#262626"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <line fill="none" stroke="currentColor" strokeLinejoin="round"
                                                  strokeWidth="2" x1="22" x2="9.218" y1="3" y2="10.083"/>
                                            <polygon
                                                fill="none"
                                                points="11.698 20.334 22 3.001 2 3.001 9.218 10.084 11.698 20.334"
                                                stroke="currentColor" strokeLinejoin="round"
                                                strokeWidth="2"/>
                                        </svg>
                                    </div>
                                </button>
                            </span>

                            {/* Favorites button */}
                            <span className="favorite-container">
                                <button className="favorite-button" type="button">
                                    <div className="favorite-internal-light">
                                        <svg aria-label="Save" className="light-favorite-" color="#8e8e8e"
                                             fill="#8e8e8e"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <polygon fill="none" points="20 21 12 13.44 4 21 4 3 20 3 20 21"
                                                     stroke="currentColor" strokeLinecap="round"
                                                     strokeLinejoin="round" strokeWidth="2"/>
                                        </svg>
                                    </div>
                                    <div className="favorite-internal-dark">
                                        <svg aria-label="Save" className="_ab6-" color="#262626" fill="#262626"
                                             height="24" role="img" viewBox="0 0 24 24" width="24">
                                            <polygon fill="none" points="20 21 12 13.44 4 21 4 3 20 3 20 21"
                                                     stroke="currentColor" strokeLinecap="round"
                                                     strokeLinejoin="round" strokeWidth="2"/>
                                        </svg>
                                    </div>
                                </button>
                            </span>
                        </section>

                        {/* Like counter section */}
                        <section className="like-counter-section">
                            <div className="like-counter-container">
                                <div className="like-counter-internal">
                                    <div className="like-counter-final">
                                        <a className="like-counter-text-container">
                                            <div className="like-counter-text">
                                                <span>181,002</span> likes
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </section>

                        {/* Description and captions */}
                        <div className="desc-caption-div">
                            <div className="desc-caption-container">
                                <div className="desc-caption-internal">
                                    <div className="desc-caption-final">

                                        {/* Profile name */}
                                        <span className="profile-name-container">
                                            <a className="profile-name-final">
                                                <span className="profile-name-text-container">
                                                    <div className="profile-name-text-actual">
                                                        {profileName}
                                                    </div>
                                                </span>
                                            </a>
                                        </span>

                                        {/* Some sort of splitter... Thing */}
                                        <span className="profile-name-splitter">
                                            &nbsp;
                                        </span>

                                        {/* Description and captions */}
                                        <span className="desc-caption-actual-container">
                                            <span className="desc-caption-actual-text">
                                                {descriptionAndCaptions}
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Days ago */}
                        <div className="days-ago-div">
                            <div className="days-ago-container">
                                <div className="days-ago-final">
                                    <a className="days-ago-text-container">
                                        <div className="days-ago-text">
                                            2 days ago
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FakeInstagramPost;