import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        // Prints example API call to show result of API and Luka Dončić's stats for the 2023 season
        exampleAPICall();

        // Prints API call that shows Top Scorers by Season (Total Points)
        exampleAPICall2();
    }

    public static void exampleAPICall() throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://nba-stats-db.herokuapp.com/api/playerdata/name/Luka Dončić")
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            // Display API response
            System.out.println(response);

            JSONObject responseJSON = new JSONObject(response.body().string());

            JSONObject JSONStats = new JSONObject(responseJSON.getJSONArray("results").get(0).toString());
            for (String key: JSONStats.keySet()) {
                System.out.println(key.concat(": ".concat(JSONStats.get(key).toString())));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exampleAPICall2() throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://nba-stats-db.herokuapp.com/api/playerdata/topscorers/total/season/2011/")
                .build();
        try {
            Response response = client.newCall(request).execute();

            // Display API response
            System.out.println(response);

            JSONObject responseJSON = new JSONObject(response.body().string());

            JSONObject JSONStats = new JSONObject(responseJSON.getJSONArray("results").get(0).toString());
            for (String key: JSONStats.keySet()) {
                System.out.println(key.concat(": ".concat(JSONStats.get(key).toString())));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
