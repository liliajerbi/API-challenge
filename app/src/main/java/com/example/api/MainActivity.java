package com.example.api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "b9870398c066a7912a85fcfd241058a4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://api.openweathermap.org/data/2.5/forecast?id=2972315&appid=" + API_KEY;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject jour = (JSONObject) list.get(i);
                                JSONArray weather = (JSONArray) jour.get("weather");

                                for (int j = 0; j < weather.length(); j++) {
                                  JSONObject weatherInfos = (JSONObject) weather.get(j);
                                    String description = weatherInfos.getString("description");
                                    TextView hello = findViewById(R.id. tvHello);
                                    hello.append(description + "\n");
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
