#!/usr/bin/perl

use JSON qw( decode_json );       # From CPAN
use LWP::UserAgent;               # From CPAN
use URI::Escape qw( uri_escape ); # From CPAN

use strict;
use warnings;

my $api_key = 'your domain reputation scoring api key';
my $base_url = 'https://api.threatintelligenceplatform.com/v1/reputation';
my $domain_name = 'threatintelligenceplatform.com';

my $url = $base_url
        . '?apiKey=' . uri_escape($api_key)
        . '&domainName=' . uri_escape($domain_name);

print 'Querying the API: ', $url, '...', "\n\n";

my $ua = LWP::UserAgent->new;
my $response = $ua->get($url);

die $response->status_line unless $response->is_success;

my $decoded_json = decode_json($response->content);
my $score = $decoded_json->{'reputationScore'};

print 'Domain name: ', $domain_name, "\n";
print 'Score: ', ($score) ? $score : 'Something went wrong.', "\n";