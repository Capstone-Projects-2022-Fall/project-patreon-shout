import React from "react";
import "./Feed.css";
import postData from "./posts.json"
import Post from "./Post";
import Searchbar from "./Searchbar";
import PatreonConnect from "./PatreonConnect";

/**
 * This is the Feed function which will appear on the home page
 * 
 * @returns Visual representation of the list of posts
 */
function Feed() {

  const posts = postData['posts']
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
    </div>
  );
}

export default Feed;