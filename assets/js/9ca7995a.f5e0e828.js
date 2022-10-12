"use strict";(self.webpackChunktu_cis_4398_docs_template=self.webpackChunktu_cis_4398_docs_template||[]).push([[1996],{3905:function(t,e,n){n.d(e,{Zo:function(){return p},kt:function(){return h}});var o=n(67294);function r(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}function i(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(t);e&&(o=o.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,o)}return n}function s(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?i(Object(n),!0).forEach((function(e){r(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}function a(t,e){if(null==t)return{};var n,o,r=function(t,e){if(null==t)return{};var n,o,r={},i=Object.keys(t);for(o=0;o<i.length;o++)n=i[o],e.indexOf(n)>=0||(r[n]=t[n]);return r}(t,e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(t);for(o=0;o<i.length;o++)n=i[o],e.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(t,n)&&(r[n]=t[n])}return r}var c=o.createContext({}),l=function(t){var e=o.useContext(c),n=e;return t&&(n="function"==typeof t?t(e):s(s({},e),t)),n},p=function(t){var e=l(t.components);return o.createElement(c.Provider,{value:e},t.children)},u={inlineCode:"code",wrapper:function(t){var e=t.children;return o.createElement(o.Fragment,{},e)}},d=o.forwardRef((function(t,e){var n=t.components,r=t.mdxType,i=t.originalType,c=t.parentName,p=a(t,["components","mdxType","originalType","parentName"]),d=l(n),h=r,m=d["".concat(c,".").concat(h)]||d[h]||u[h]||i;return n?o.createElement(m,s(s({ref:e},p),{},{components:n})):o.createElement(m,s({ref:e},p))}));function h(t,e){var n=arguments,r=e&&e.mdxType;if("string"==typeof t||r){var i=n.length,s=new Array(i);s[0]=d;var a={};for(var c in e)hasOwnProperty.call(e,c)&&(a[c]=e[c]);a.originalType=t,a.mdxType="string"==typeof t?t:r,s[1]=a;for(var l=2;l<i;l++)s[l]=n[l];return o.createElement.apply(null,s)}return o.createElement.apply(null,n)}d.displayName="MDXCreateElement"},62680:function(t,e,n){n.r(e),n.d(e,{assets:function(){return p},contentTitle:function(){return c},default:function(){return h},frontMatter:function(){return a},metadata:function(){return l},toc:function(){return u}});var o=n(83117),r=n(80102),i=(n(67294),n(3905)),s=["components"],a={sidebar_position:2},c="Integration tests",l={unversionedId:"testing/integration-testing",id:"testing/integration-testing",title:"Integration tests",description:"Integration testing will be done using Mockito to mock API endpoints in Java to ensure the application properly handles this communication. Jest will be used alongside React to mock objects and ensure smooth user flow.",source:"@site/docs/testing/integration-testing.md",sourceDirName:"testing",slug:"/testing/integration-testing",permalink:"/project-patreon-shout/docs/testing/integration-testing",draft:!1,editUrl:"https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/edit/main/documentation/docs/testing/integration-testing.md",tags:[],version:"current",sidebarPosition:2,frontMatter:{sidebar_position:2},sidebar:"docsSidebar",previous:{title:"Unit tests",permalink:"/project-patreon-shout/docs/testing/unit-testing"},next:{title:"Acceptance test",permalink:"/project-patreon-shout/docs/testing/acceptence-testing"}},p={},u=[{value:"Creator Social Platform Post Test:",id:"creator-social-platform-post-test",level:3},{value:"Post Filtering Test:",id:"post-filtering-test",level:3},{value:"Searching Posts Test:",id:"searching-posts-test",level:3},{value:"Recommendation System Test:",id:"recommendation-system-test",level:3},{value:"Main Feed Test:",id:"main-feed-test",level:3},{value:"Discord Integration Test:",id:"discord-integration-test",level:3},{value:"Instagram Integration Test:",id:"instagram-integration-test",level:3},{value:"Twitter Integration Test:",id:"twitter-integration-test",level:3}],d={toc:u};function h(t){var e=t.components,n=(0,r.Z)(t,s);return(0,i.kt)("wrapper",(0,o.Z)({},d,n,{components:e,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"integration-tests"},"Integration tests"),(0,i.kt)("p",null,"Integration testing will be done using Mockito to mock API endpoints in Java to ensure the application properly handles this communication. Jest will be used alongside React to mock objects and ensure smooth user flow."),(0,i.kt)("h3",{id:"creator-social-platform-post-test"},"Creator Social Platform Post Test:"),(0,i.kt)("p",null,"Objective: Test the interface between a creators linked Patreon and other social platforms when a new post is published"),(0,i.kt)("p",null,"Description: Link Patreon, Discord, Instagram, and Twitter accounts. Enable crossposting in Creator settings. Publish a new post on Patreon.\nExpected Result: New post is also posted on Discord, Instagram, and Twitter with correct formatting"),(0,i.kt)("h3",{id:"post-filtering-test"},"Post Filtering Test:"),(0,i.kt)("p",null,"Objective: Test the interface between the filter component and feed component"),(0,i.kt)("p",null,"Description: On the main feed, add a new filter so the feed sorts by creator name\nExpected Result: The main feed now sorts posts alphabetically by creator name"),(0,i.kt)("h3",{id:"searching-posts-test"},"Searching Posts Test:"),(0,i.kt)("p",null,"Objective: Test the search bar and the search bar button components"),(0,i.kt)("p",null,"Description: On the main feed, enter text into the search bar with text, and click the search button\nExpected Result: The main feed now contains posts that contain text given in the search bar "),(0,i.kt)("h3",{id:"recommendation-system-test"},"Recommendation System Test:"),(0,i.kt)("p",null,"Objective: Test the implementation of the recommendation system"),(0,i.kt)("p",null,"Description: \u201cLike\u201d several posts on the main feed, and click \u201cshow recommendations\u201d\nExpected Result: The right sidebar should populate with "),(0,i.kt)("h3",{id:"main-feed-test"},"Main Feed Test:"),(0,i.kt)("p",null,"Objective: Test the main feed functionality"),(0,i.kt)("p",null,"Description: Follow creators, view creator posts on main feed, load more posts by scrolling\nExpected Result: Posts should populate the main feed after creators are followed, and more posts are loaded when scrolling"),(0,i.kt)("h3",{id:"discord-integration-test"},"Discord Integration Test:"),(0,i.kt)("p",null,"Objective: Test that users can view automated Patreon posts on Discord"),(0,i.kt)("p",null,"Description:  Find the content creator\u2019s Discord server that is linked with Patreon Shout, view new Patreon posts on Discord channel\nExpected Result: Patreon posts should show up in the Discord channel of the user when published to Patreon"),(0,i.kt)("h3",{id:"instagram-integration-test"},"Instagram Integration Test:"),(0,i.kt)("p",null,"Objective: Test that users can view automated Patreon posts on Instagram"),(0,i.kt)("p",null,"Description: Find the content creator\u2019s account that is linked with Patreon Shout, follow the content creator, view new Patreon posts on Instagram\nExpected Result: Patreon posts should show up in the Instagram feed of the user when published to Patreon"),(0,i.kt)("h3",{id:"twitter-integration-test"},"Twitter Integration Test:"),(0,i.kt)("p",null,"Objective: Test that users can view automated Patreon posts on Twitter"),(0,i.kt)("p",null,"Description: Find the content creator\u2019s account that is linked with Patreon Shout, follow the content creator, view new Patreon posts on Twitter\nExpected Result: Patreon posts should show up in the Twitter feed of the user when published to Patreon"))}h.isMDXComponent=!0}}]);