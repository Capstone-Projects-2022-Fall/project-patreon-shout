import React, {useEffect, useState} from "react";
import "./home_css/Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
import PatreonConnect from "./PatreonConnect";
import { getPosts } from '../services/api/posts'

/**
 * This is the Feed function which will appear on the home page
 *
 * @returns Visual representation of the list of posts
 */


function Feed() {

    const [data, setData] = useState([]);
    //let postscount = 0;
    //const [postList, setPostList] = useState([]);

    // useEffect(() => {
    //     postscount += 1;
    //     let mounted = true;
    //     getPosts("Alex Sawicki")
    //         .then(items => {
    //             if (mounted) {
    //                 setPostList(items)
    //             }
    //         })
    //     return () => {
    //     mounted = false;
    //     postscount -= 1;}
    // }, [])

    let postscount = 14

    return (
        <div className="feed">
            <div className="feed__header">
                <h2>Home</h2>
            </div>
            <Searchbar
                data={data}
                setData={setData}
            />
            <PatreonConnect/>
            <Post
                displayName={data}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
            <Post
                displayName={"Creator"}
                username={"Thatoneguywhocreates"}
                verified={true}
                text={"One upon a time there was a land far far away where things could be found when searched"}
                avatar={"yes"}
                image={"yes"}
            />
        </div>
    );
}
export default Feed;