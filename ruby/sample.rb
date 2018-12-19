require 'erb'
require 'json'
require 'net/https'
require 'uri'
require 'yaml' # only needed to print the returned result in a very pretty way

api_key = 'Your-API-key'
domain = 'threatintelligenceplatform.com'
mode = 'fast'

url = 'https://api.threatintelligenceplatform.com/v1/reputation?' \
      'apiKey=' + ERB::Util.url_encode(api_key) +
      '&domainName=' + ERB::Util.url_encode(domain) +
      '&mode=' + ERB::Util.url_encode(mode)

# Open the resource
buffer = Net::HTTP.get(URI.parse(url))

# Parse the JSON result
result = JSON.parse(buffer)

# Print out a nice informative string
puts "JSON:\n" + result.to_yaml + "\n"