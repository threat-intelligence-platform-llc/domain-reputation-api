import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.*;

public class DomainReputationApi
{
    private static final String BASE_URL =
            "https://api.threatintelligenceplatform.com/v1/reputation";

    private String apiKey;

    public static void main(String[] args)
    {
        DomainReputationApi rss = new DomainReputationApi();
        rss.setApiKey("your domain reputation api key");

        try {
            System.out.println(rss.sendGet("threatintelligenceplatform.com"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String sendGet(String domain) throws Exception
    {
        String userAgent = "Mozilla/5.0";
        String url = this.buildUrl(domain);

        URL obj = new URL(url);

        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return prettyJson(response.toString());
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    private String buildUrl(String domain) throws IOException
    {
        return DomainReputationApi.BASE_URL
                + "?apiKey=" + URLEncoder.encode(getApiKey(), "UTF-8")
                + "&domainName=" + URLEncoder.encode(domain, "UTF-8")
                + "&mode=full";
    }

    private String getApiKey()
    {
        return this.apiKey;
    }

    private String prettyJson(String jsonString)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);

        return gson.toJson(je);
    }
}