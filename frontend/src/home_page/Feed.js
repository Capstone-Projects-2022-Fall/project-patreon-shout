import React, { useState } from "react";
import "./Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
import PatreonConnect from "./PatreonConnect";

/**
 * This is the Feed function which will appear on the home page
 * 
 * @returns Visual representation of the list of posts
 */
function Feed() {
  const [posts] = useState([]);

  return (
    <div className="feed">
      <div className="feed__header">
        <h2>Home</h2>
      </div>
      <Searchbar />
      <PatreonConnect />
      {posts.map((post) => (
        <Post
          displayName={post.displayName}
          username={post.username}
          verified={post.verified}
          text={post.text}
          avatar={post.avatar}
          image={post.image}
        />
      ))}
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Alex"}
        username={"ALLLLLLEX"}
        verified={false}
        text={"I HATE THIS WEBSITE MAN"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Chris"}
        username={"ChrystalLite"}
        verified={"check"}
        text={"Man this needs a LOT of work"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
      <Post
        displayName={"Jones"}
        username={"BoneyJones"}
        verified={"check"}
        text={"Wow this is a cool website"}
        avatar={"post.avatar"}
        image={"post.image"}
      />
    </div>
  );
}

export default Feed;