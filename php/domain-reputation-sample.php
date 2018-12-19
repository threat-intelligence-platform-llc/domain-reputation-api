<?php

$apiUrl = 'https://api.threatintelligenceplatform.com/v1/reputation';

$params = array(
    'apiKey' => 'Your domain reputation API key',
    'domainName' => 'threatintelligenceplatform.com',
    'mode' => 'fast'
);

$url = $apiUrl . '?' . http_build_query($params, '', '&');

print($url . PHP_EOL . PHP_EOL);

$response = file_get_contents($url);

$res = json_decode($response);

if (! $res)
    return;

$score = $res->reputationScore;
if (! $score)
    return;

echo 'Reputation score: ' . $score . PHP_EOL;