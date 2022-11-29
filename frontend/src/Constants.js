const prod = {
    url: {
        API_URL: 'https://backend.outofstonk.com'
    }
};

const dev = {
    url: {
        // API_URL: 'https://ayser.backend.outofstonk.com:5000'
        API_URL: 'http://localhost:5000'
    }
};

// export const config = process.env.NODE_ENV === 'development' ? dev : prod;
export const config = prod;