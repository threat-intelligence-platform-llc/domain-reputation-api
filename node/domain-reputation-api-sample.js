let https = require('https');
let querystring = require('querystring');

const apiUrl = 'https://api.threatintelligenceplatform.com/v1/reputation';

const parameters = {
    apiKey: 'Your Domain Reputation API key',
    domainName: 'threatintelligenceplatform.com',
    mode: 'full'
};

let url = apiUrl + '?' + querystring.stringify(parameters);

https.get(url, function (res) {
    let rawData = '';

    res.on('data', function(chunk) {
        rawData += chunk;
    });

    res.on('end', function () {
        try {
            let parsedData = JSON.parse(rawData);

            if (parsedData.reputationScore)
                console.log(
                    'Score: ' + parsedData.reputationScore);
            else
                console.dir(parsedData, {colors: true});
        }
        catch (e) {
            console.log(e.message);
        }
    });
}).on('error', function(e) {
    console.log('Error: ' + e.message);
});