package com.example.accesstradetool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adapter.TopSellAdapter;

import com.example.api.topsell.TopSellService;
import com.example.conts.Contstan;
import com.example.model.JsonTopSell;
import com.example.model.TopSellAPI;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSellActivity extends AppCompatActivity {
    RecyclerView rec_Topsell;
    TopSellAdapter topSellAdapter;
    ArrayList<TopSellAPI> arrTopSell;
    EditText edt_date_from, edt_date_to, edt_merchant;
    Button btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sell);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiTopSell(edt_date_from.getText().toString(),
                        edt_date_to.getText().toString());
                topSellAdapter.notifyDataSetChanged();
                /*GetTopSellTask task= new GetTopSellTask();
                task.execute("date_from="+edt_date_from.getText().toString()+"&date_to="+edt_date_to.getText().toString());*/
                //getData();
            }
        });
    }

    private void addControls() {
        arrTopSell = new ArrayList<>();
        rec_Topsell = findViewById(R.id.recy_TopSell);
        rec_Topsell.setLayoutManager(new LinearLayoutManager(TopSellActivity.this));
        topSellAdapter = new TopSellAdapter(TopSellActivity.this, arrTopSell);
        rec_Topsell.setAdapter(topSellAdapter);
        edt_date_from = findViewById(R.id.edt_date_from);
        edt_date_to = findViewById(R.id.edt_date_to);
        edt_merchant = findViewById(R.id.edt_merchant);
        btn_go = findViewById(R.id.btn_go);
    }

    private void getData() {
        try {
            String url = "https://api.accesstrade.vn/v1/top_products?" + "date_from=" + edt_date_from.getText().toString() + "&date_to=" + edt_date_to.getText().toString();
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("Content-Type", "application/json");
            client.addHeader("Authorization", "Token " + Contstan.ACCESS_KEY);
            client.get(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                    // Handle resulting parsed JSON response here
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject item = jsonArray.getJSONObject(i);
                            TopSellAPI topSellAPI = new TopSellAPI();
                            topSellAPI.setName(item.getString("name"));
                            topSellAPI.setImage(item.getString("image"));
                            topSellAPI.setPrice(item.getString("price"));
                            topSellAPI.setDiscount(item.getString("discount"));
                            arrTopSell.add(topSellAPI);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            });
            topSellAdapter.notifyDataSetChanged();
        } catch (Exception ex) {

        }
    }

    private void callApiTopSell(String dateFrom, String dateTo) {
        TopSellService.getInstance().getTopSell(new Callback<JsonTopSell>() {
            @Override
            public void onResponse(Call<JsonTopSell> call, Response<JsonTopSell> response) {
                if (response.isSuccessful()) {
                    JsonTopSell topSellAPIS = response.body();
                    //Toast.makeText(TopSellActivity.this, topSellAPIS.get(1).getName(), Toast.LENGTH_SHORT).show();
                    List<TopSellAPI> arr=topSellAPIS.getData();
                    arrTopSell.addAll(arr);
                    topSellAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(TopSellActivity.this,response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonTopSell> call, Throwable t) {
                Toast.makeText(TopSellActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, dateFrom, dateTo);
        class GetTopSellTask extends AsyncTask<String, Void, ArrayList<TopSellAPI>> {

            @Override
            protected void onPostExecute(ArrayList<TopSellAPI> topSellAPIS) {
                super.onPostExecute(topSellAPIS);
                arrTopSell.addAll(topSellAPIS);
                topSellAdapter.notifyDataSetChanged();
            }

            @Override
            protected ArrayList<TopSellAPI> doInBackground(String... strings) {
                ArrayList<TopSellAPI> topSellAPIS = new ArrayList<>();
                try {
                    URL url = new URL("https://api.accesstrade.vn/v1/top_products?" + strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    connection.setRequestProperty("Authorization", "Token " + Contstan.ACCESS_KEY);

                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(builder.toString());
                    JSONArray arrItem = jsonObject.getJSONArray("data");
                    for (int i = 0; i > arrItem.length(); i++) {
                        JSONObject item = arrItem.getJSONObject(i);
                        TopSellAPI topSellAPI = new TopSellAPI();
                        topSellAPI.setName(item.getString("name"));
                        topSellAPI.setImage(item.getString("image"));
                        topSellAPI.setPrice(item.getString("price"));
                        topSellAPI.setDiscount(item.getString("discount"));
                        topSellAPIS.add(topSellAPI);
                    }
                } catch (Exception ex) {

                }

                return topSellAPIS;
            }
        }
    }
}
