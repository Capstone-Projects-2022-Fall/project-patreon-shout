
const prod = {
    url: {
        API_URL: 'https://prodprod.backend.outofstonk.com'
    }
};

const dev = {
    url: {
        // API_URL: 'https://ayser.backend.outofstonk.com'
        API_URL: 'http://localhost:5000'
    }
};

// export const config = process.env.NODE_ENV === 'development' ? dev : prod;
export const config = prod; // = prod