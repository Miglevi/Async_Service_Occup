package com.example.madt1116;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Service_occupation {
    public static String getService_occupation(InputStream stream) throws IOException {
        StringBuilder data = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        }

        String result;
        try {
            JSONObject jData = new JSONObject(data.toString());
            JSONArray dataJSONArray = jData.getJSONArray("data");
            StringBuilder Occupation = new StringBuilder();
            for (int i = 0; i < dataJSONArray.length(); i++) {
                JSONObject dataNode = dataJSONArray.getJSONObject(i);
                String year = dataNode.optString("Year");
                String gender = dataNode.optString("Gender");
                int population = dataNode.optInt("Total Population");
                Occupation.append("Year: ").append(year).append(", Gender: ").append(gender).append(", Total Population: ").append(population).append("\n");
            }
            result = Occupation.toString();
        } catch (JSONException e) {
            result = "Invalid JSON data " + e.getMessage();
        }
        return result;
    }
}
