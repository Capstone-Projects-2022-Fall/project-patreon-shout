
const prod = {
    url: {
        API_URL: 'http://patreon-shout-gateway.us-east-2.elasticbeanstalk.com'
    }
};

const dev = {
    url: {
        API_URL: 'http://localhost:5000'
    }
};

export const config = process.env.NODE_ENV === 'development' ? dev : prod;