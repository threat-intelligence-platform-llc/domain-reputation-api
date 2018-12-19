#!/usr/bin/perl

use HTTP::Request::Common qw{ POST }; # From CPAN
use JSON qw( decode_json );           # From CPAN
use LWP::UserAgent;                   # From CPAN

use strict;
use warnings;

my $api_key = 'Your-API-key';
my $mode = 'full';
my $domain = 'threatintelligenceplatform.com';

my $url = 'https://api.threatintelligenceplatform.com/v1/reputation?'
    . 'apiKey=' . $api_key
    . '&mode=' . $mode
    . '&domainName=' . $domain;

my $responseJson = JSON->new->decode(get_data());
print "JSON\n---\n";
print JSON->new->pretty->encode($responseJson);

sub get_data {
    my $ua = LWP::UserAgent->new(ssl_opts => { verify_hostname => 0 });
    my $req = HTTP::Request->new('GET', $url);

    $req->header('Accept', 'application/json');

    my $response = $ua->request($req);

    return $response->content;
}