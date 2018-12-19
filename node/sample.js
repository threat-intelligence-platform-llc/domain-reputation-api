var https = require('https');

// Fill in your details
var api_key = 'Your-api-key';
var mode = "full";
var domain = "threatintelligenceplatform.com";

var url = "https://api.threatintelligenceplatform.com/v1/reputation?"
    + "domainName=" + domain
    + "&mode=" + mode
    + "&apiKey=" + api_key;

var req = https.get(url, function(res)  {
    var str = '';
    res.on('data', function(chunk) {
        str += chunk;
    });
    res.on('end', function() {
        console.log(JSON.parse(str));
    });

});

// Handle errors
req.on('error', function(e) {
    console.error(e);
});
