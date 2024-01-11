package net.reywob.grizzcycle;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    public HttpAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e("HttpAsyncTask", "Error in doInBackground: " + e.getMessage());
            Toast.makeText(context, "Error in doInBackground", Toast.LENGTH_LONG).show();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("HttpAsyncTask", "onPostExecute: " + result);
        // Check if the server returns an error, otherwise consider the return a valid DB result
        if (result != null && result.startsWith("Error:")) {
            // Show a Toast indicating that there was no response
            Toast.makeText(context, "The server did not respond.", Toast.LENGTH_LONG).show();
        } else {
            // Parse the returned JSON string into a JSONObject
            try {
                JSONObject jsonResult = new JSONObject(result);
                // Extract values from the JSONObject
                String itemBarcode = jsonResult.getString("item_barcodes");
                String itemName = jsonResult.getString("item_name");
                String itemType = jsonResult.getString("item_type");
                String itemMaterial = jsonResult.getString("item_material");
                int isRecyclable = jsonResult.getInt("is_recyclable");

                // Create an Intent to start the ResultsActivity
                Intent intent = new Intent(context, ResultsActivity.class);

                // Pass the parsed variables to the new activity
                intent.putExtra("itemBarcode", itemBarcode);
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemType", itemType);
                intent.putExtra("itemMaterial", itemMaterial);
                intent.putExtra("isRecyclable", isRecyclable);

                // Start the new activity
                context.startActivity(intent);

            } catch (JSONException e) {
                // If there is a JSON parsing error
                Log.e("HttpAsyncTask", "Error parsing JSON: " + e.getMessage());
                Toast.makeText(context, "Error parsing JSON", Toast.LENGTH_SHORT).show();
            }
        }
    }
}