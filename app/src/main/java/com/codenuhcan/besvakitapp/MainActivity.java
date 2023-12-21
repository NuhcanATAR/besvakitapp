package com.codenuhcan.besvakitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView textImsak, textGunes, textOgle, textIkindi, textAksam, textYatsi, txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtDate = findViewById(R.id.txtDate);
        textImsak = findViewById(R.id.txtImsak);
        textGunes = findViewById(R.id.txtGunes);
        textOgle = findViewById(R.id.txtOgle);
        textIkindi = findViewById(R.id.txtIkındı);
        textYatsi = findViewById(R.id.txtYatsı);
        textAksam = findViewById(R.id.txtAksam);

        requestQueue = Volley.newRequestQueue(this);
        HttpRequest request = new HttpRequest(Request.Method.GET, "https://api.collectapi.com/pray/all?data.city=corum",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response);

                            JSONArray array = new JSONArray(o.getString("result"));

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                switch (object.getString("vakit")) {
                                    case "İmsak":
                                        textImsak.setText(object.getString("saat"));
                                        break;
                                    case "Güneş":
                                        textGunes.setText(object.getString("saat"));
                                        break;
                                    case "Öğle":
                                        textOgle.setText(object.getString("saat"));
                                        break;
                                    case "İkindi":
                                        textIkindi.setText(object.getString("saat"));
                                        break;
                                    case "Akşam":
                                        textAksam.setText(object.getString("saat"));
                                        break;
                                    default:
                                        textYatsi.setText(object.getString("saat"));
                                        break;
                                }
                            }


                            final Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    String currentDateTime = getCurrentDateTime();

                                    txtDate.setText(currentDateTime);

                                    handler.postDelayed(this, 1000);
                                }
                            });

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);
    }

    private String getCurrentDateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
}

class HttpRequest extends StringRequest {
    public HttpRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("authorization", "apikey KEY");

        return headers;
    }
}
