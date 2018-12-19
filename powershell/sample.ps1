$url = 'https://api.threatintelligenceplatform.com/v1/reputation?'

$api_key = 'Your-API-key'
$domain = 'threatintelligenceplatform.com'
$mode = 'full'

$uri = $url`
     + 'apiKey=' + [uri]::EscapeDataString($api_key)`
     + '&domainName=' + [uri]::EscapeDataString($domain)`
     + '&mode=' + [uri]::EscapeDataString($mode)

$j = Invoke-WebRequest -Uri $uri
echo "JSON:`n---" $j.content "`n"
