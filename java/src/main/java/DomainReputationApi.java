import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.*;


public class DomainReputationApi {

    private String apiKey;

    protected static final String BASE_URL =
            "https://api.threatintelligenceplatform.com/v1/reputation";

    public DomainReputationApi(String apiKey) {
        this.apiKey = apiKey;
    }


    public static void main(String[] args) {
        DomainReputationApi rss = new DomainReputationApi("Your-API-key");

        try {
            String response = rss.sendGet();
            System.out.println(rss.prettyJson(response));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String sendGet() throws Exception
    {
        String userAgent = "Mozilla/5.0";
        String url = this.buildUrl("threatintelligenceplatform.com");

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

        return response.toString();
    }

    protected String buildUrl(String domain) {
        StringBuffer url = new StringBuffer(DomainReputationApi.BASE_URL);
        url.append("?");
        url.append("apiKey=");
        url.append(this.apiKey);
        url.append("&domainName=");
        url.append(domain);
        url.append("&mode=full");

        return url.toString();
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    protected String getApiKey()
    {
        return this.apiKey;
    }

    private String prettyJson(String jsonString)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }
}
