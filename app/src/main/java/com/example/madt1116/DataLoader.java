package com.example.madt1116;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
public class DataLoader extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... params) {
        try {
            return DataManager.getValuesFromApi(params[0]);
        } catch (IOException ex) {
            return String.format("Some error occured => %s", ex.getMessage());
        }
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}