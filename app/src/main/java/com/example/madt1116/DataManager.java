package com.example.madt1116;
import com.example.madt1116.Service_occupation;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import static com.example.asynctaskwithapiexample.utilities.Constants.JAV_POP_URL;
public class DataManager {
    public static String getValuesFromApi(String apiCode) throws IOException {
        try (InputStream apiContentStream = downloadUrlContent(apiCode)) {
            return Service_occupation.getService_occupation(apiContentStream);
        }
    }
    // Routine that creates and calls GET request to web page
    private static InputStream downloadUrlContent(String apiCode) throws IOException {
        URL url = new URL(apiCode);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}