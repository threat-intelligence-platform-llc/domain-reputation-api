using System;
using System.Net;

using Newtonsoft.Json;

namespace DomainReputationApi
{
    internal static class DomainReputationApiSample
    {
        private static void Main()
        {
            var sample = new DomainReputationApiClient
            {
                ApiKey = "Your domain reputation api key"
            };

            const string domain = "threatintelligenceplatform.com";

            // Download JSON
            var result = sample.SendGet(domain);

            // Print a nice informative string
            PrintResponse(result);
        }

        private static void PrintResponse(string response)
        {
            dynamic responseObject = JsonConvert.DeserializeObject(response);

            if (responseObject != null)
            {
                Console.Write("Score: " + responseObject.reputationScore);
                Console.WriteLine("\n--------------------------------");
                return;
            }

            Console.WriteLine();
        }
    }

    public class DomainReputationApiClient
    {
        public string ApiKey { private get; set; }

        private const string Url =
            "https://api.threatintelligenceplatform.com/v1/reputation";

        public string SendGet(string domain)
        {
            var requestParams = "?domainName=" + Uri.EscapeDataString(domain)
                              + "&apiKey=" + Uri.EscapeDataString(ApiKey);

            var fullUrl = Url + requestParams;

            Console.Write("Sending request to: " + fullUrl + "\n");

            // Download JSON into a string
            var result = new WebClient().DownloadString(fullUrl);

            return result;
        }
    }
}