import React from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import HomepageFeatures from '@site/src/components/HomepageFeatures';
import ProjectReadme from "../components/ReademeMD";
import styles from './index.module.css';
import ForReview from "../components/ForReview";

function HomepageHeader() {
  const {siteConfig} = useDocusaurusContext();
  return (
    <header className={clsx('hero hero--primary', styles.heroBanner)}>
      <div className="container">
        <h1 className="hero__title">{siteConfig.title}</h1>
        <p className="hero__subtitle">{siteConfig.tagline}</p>
        <div className={styles.buttons}>
             {/* TODO: Change me to your project's tutorial*/ }
        </div>
      </div>
    </header>
  );
}


export default function Home() {
  const {siteConfig} = useDocusaurusContext();
  return (
    <Layout
        title={`${siteConfig.title}`}
        description="<head />">
        <div style={{marginRight:"10%",marginLeft:"10%"}}><ForReview/></div>
        <HomepageHeader/>
        <main>
            <HomepageFeatures/>
            <ProjectReadme/>
        </main>
    </Layout>
  );
}
