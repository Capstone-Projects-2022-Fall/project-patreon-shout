"use strict";(self.webpackChunktu_cis_4398_docs_template=self.webpackChunktu_cis_4398_docs_template||[]).push([[650],{3905:function(e,t,n){n.d(t,{Zo:function(){return u},kt:function(){return f}});var r=n(67294);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var s=r.createContext({}),l=function(e){var t=r.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},u=function(e){var t=l(e.components);return r.createElement(s.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,i=e.originalType,s=e.parentName,u=c(e,["components","mdxType","originalType","parentName"]),d=l(n),f=o,h=d["".concat(s,".").concat(f)]||d[f]||p[f]||i;return n?r.createElement(h,a(a({ref:t},u),{},{components:n})):r.createElement(h,a({ref:t},u))}));function f(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var i=n.length,a=new Array(i);a[0]=d;var c={};for(var s in t)hasOwnProperty.call(t,s)&&(c[s]=t[s]);c.originalType=e,c.mdxType="string"==typeof e?e:o,a[1]=c;for(var l=2;l<i;l++)a[l]=n[l];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},89935:function(e,t,n){n.r(t),n.d(t,{assets:function(){return u},contentTitle:function(){return s},default:function(){return f},frontMatter:function(){return c},metadata:function(){return l},toc:function(){return p}});var r=n(83117),o=n(80102),i=(n(67294),n(3905)),a=["components"],c={sidebar_position:3},s="Acceptance test",l={unversionedId:"testing/acceptence-testing",id:"testing/acceptence-testing",title:"Acceptance test",description:"Use Case Acceptance Testing will be performed to ensure that certain features are functional and match the users\u2019 expectations.",source:"@site/docs/testing/acceptence-testing.md",sourceDirName:"testing",slug:"/testing/acceptence-testing",permalink:"/project-patreon-shout/docs/testing/acceptence-testing",draft:!1,editUrl:"https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/edit/main/documentation/docs/testing/acceptence-testing.md",tags:[],version:"current",sidebarPosition:3,frontMatter:{sidebar_position:3},sidebar:"docsSidebar",previous:{title:"Integration tests",permalink:"/project-patreon-shout/docs/testing/integration-testing"}},u={},p=[{value:"Account Login and Registration",id:"account-login-and-registration",level:3},{value:"Patreon Posts Feed",id:"patreon-posts-feed",level:3},{value:"Patreon Creator Lists",id:"patreon-creator-lists",level:3},{value:"Patreon Link",id:"patreon-link",level:3},{value:"Social Platform Link",id:"social-platform-link",level:3},{value:"Credential Security",id:"credential-security",level:3},{value:"Testing Sheet",id:"testing-sheet",level:2}],d={toc:p};function f(e){var t=e.components,n=(0,o.Z)(e,a);return(0,i.kt)("wrapper",(0,r.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"acceptance-test"},"Acceptance test"),(0,i.kt)("p",null,"Use Case Acceptance Testing will be performed to ensure that certain features are functional and match the users\u2019 expectations."),(0,i.kt)("h3",{id:"account-login-and-registration"},"Account Login and Registration"),(0,i.kt)("p",null,"Actions: Open Patreon Shout website, select \u201cregister\u201d, enter new account information, click \u201cregister\u201d, go to the login page, fill in text boxes with information used during registration, click \u201clogin\u201d"),(0,i.kt)("p",null,"Expected Result: You should be redirected to the home page where you will see the main feed"),(0,i.kt)("h3",{id:"patreon-posts-feed"},"Patreon Posts Feed"),(0,i.kt)("p",null,"Actions: Scroll up to view new posts, search for a particular creator in the search bar at the top of the main feed, click the filter button to the right of the search bar, filter posts by shown categories via checkbox"),(0,i.kt)("p",null,"Expected Result: You should be able to view Patreon posts, search for creators, and filter through Patreon posts on the main feed"),(0,i.kt)("h3",{id:"patreon-creator-lists"},"Patreon Creator Lists"),(0,i.kt)("p",null,"Actions: Click the \u201cLists\u201d button to the right of the main feed, click \u201ccreate new list\u201d, search and add creators into your list, name your list, click \u201cFinish\u201d, click on the newly created list to have that list populate the main feed with Patreon posts from the selected creators"),(0,i.kt)("p",null,"Expected Result: View specific creator posts from a custom list of creators on the main feed"),(0,i.kt)("h3",{id:"patreon-link"},"Patreon Link"),(0,i.kt)("p",null,"Actions: When navigating to the Creator settings you are greeted with a \u201cLink Patreon\u201d button.  Click it and a new browser window pops up with Patreon asking if you would like to allow or deny PatreonShout specific access to my account\u2019s details.  Click allow."),(0,i.kt)("p",null,"Expected Result: You will be taken back to the Creator settings with their Patreon account linked to their PatreonShout account, revealing Creator integration features. "),(0,i.kt)("h3",{id:"social-platform-link"},"Social Platform Link"),(0,i.kt)("p",null,"Actions: Click \u201cSettings\u201d to the right of the main feed, navigate to the creator settings tab, link your Patreon account, select a social platform to link to, provide the social platform api token in the textbox, publish a post on Patreon"),(0,i.kt)("p",null,"Expected Result: Automatically send your newly published Patreon post to another social platform"),(0,i.kt)("h3",{id:"credential-security"},"Credential Security"),(0,i.kt)("p",null,"Actions: Register an account with your desired credentials."),(0,i.kt)("p",null,"Expected Result: The given password is securely stored on the website\u2019s database."),(0,i.kt)("h2",{id:"testing-sheet"},"Testing Sheet"),(0,i.kt)("p",null,(0,i.kt)("img",{parentName:"p",src:"https://user-images.githubusercontent.com/29709311/195232930-7e2143fd-19e9-473e-b403-578539191ea2.png",alt:"excel"})))}f.isMDXComponent=!0}}]);