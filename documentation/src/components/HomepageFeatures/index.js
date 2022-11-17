import React from 'react';
import clsx from 'clsx';
import styles from './styles.module.css';
/*TODO: Add images here bois*/
const FeatureList = [
  {
    title: 'Main Feed',
    Svg: require('@site/static/img/undraw_docusaurus_mountain.svg').default,
    description: (
      <>
        An easy to use feed for patrons to view all of their 
        followed creators in the same space.
      </>
    ),
  },
  {
    title: 'Cross Posting',
    Svg: require('@site/static/img/undraw_docusaurus_tree.svg').default,
    description: (
      <>
        Whenever a creator decides they would like to post new
        content on Patreon, they can now choose to enable crossposting
        allowing that post to also be send to their Twitter and Discord.
      </>
    ),
  },
  {
    title: 'Post Interaction',
    Svg: require('@site/static/img/undraw_docusaurus_react.svg').default,
    description: (
      <>
        Users can now interact with their feed in all kinds of new and exciting
        ways, such as searching, filtering, creating new filters, favoriting posts
        tagging posts, and adding posts to lists.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
