$url = 'https://api.threatintelligenceplatform.com/v1/reputation?'

$api_key = 'Your reputation scoring api key'
$domain = 'threatintelligenceplatform.com'
$mode = 'full'

$uri = $url `
     + 'apiKey=' + [uri]::EscapeDataString($api_key) `
     + '&domainName=' + [uri]::EscapeDataString($domain) `
     + '&mode=' + [uri]::EscapeDataString($mode)

$res = Invoke-WebRequest -Uri $uri -UseBasicParsing | ConvertFrom-Json

echo "`nReputation score: $($res.reputationScore)"