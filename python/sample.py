try:
    from urllib.request import urlopen, pathname2url
except ImportError:
    from urllib import pathname2url
    from urllib2 import urlopen

import json


def print_response(txt):
    response_json = json.loads(txt)
    print(json.dumps(response_json, indent=4, sort_keys=True))


domain = 'threatintelligenceplatform.com'
api_key = 'Your-API-key'
mode = 'full'

url = 'https://api.threatintelligenceplatform.com/v1/reputation?'\
    + 'apiKey=' + pathname2url(api_key)\
    + '&domainName=' + pathname2url(domain)\
    + '&mode' + pathname2url(mode)

print_response(urlopen(url).read().decode('utf8'))