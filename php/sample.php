<?php
$apiKey = 'Your-API-key';
$header = "Accept: application/json\r\n";
$mode = 'full';
$domain = 'threatintelligenceplatform.com';

$url = 'https://api.threatintelligenceplatform.com/v1/reputation?'
    . 'apiKey=' . $apiKey
    . '&mode=' . $mode
    . '&domainName=' . $domain;

$options = array(
    'http' => array(
        'method' => 'GET',
        'header' => $header,
    )
);
print(file_get_contents($url,false,stream_context_create($options)) .PHP_EOL);