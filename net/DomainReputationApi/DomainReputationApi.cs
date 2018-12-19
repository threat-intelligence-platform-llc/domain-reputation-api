using System;

namespace DomainReputationApi
{
    internal class DomainReputationApi
    {
        public static void Main(string[] args)
        {
            const string apiKey = "Your-API-key";
            const string domain = "threatintelligenceplatform.com";
            const string mode = "full";

            var url =
                "https://api.threatintelligenceplatform.com/v1/reputation?"
                + "apiKey=" + Uri.EscapeDataString(apiKey)
                + "&domainName=" + Uri.EscapeDataString(domain)
                + "&mode=" + Uri.EscapeDataString(mode);
            
            dynamic result = new System.Net.WebClient().DownloadString(url);
            
            Console.WriteLine(result);

            // Prevent command window from automatically closing
            Console.WriteLine("Press any key to continue...");
            Console.ReadKey();
        }
    }
}