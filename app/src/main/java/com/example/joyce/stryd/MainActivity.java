package com.example.joyce.stryd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnParse;
    ListView listResult;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        btnParse = (Button) findViewById(R.id.btn_parse);
        listResult = (ListView) findViewById(R.id.list_result);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

        Request request = new Request.Builder()
                .url("https://www.stryd.com/b/interview/data")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                JSONObject jsonResponse;

                try {
                    String json = response.body().string();

                    jsonResponse = new JSONObject(json);
                    JSONArray jsonMainNode_power = jsonResponse.optJSONArray("total_power_list");
                    JSONArray jsonMainNode_hr = jsonResponse.optJSONArray("heart_rate_list");

                    Log.d("total_power_list", String.valueOf(jsonMainNode_power));
                    Log.d("heart_rate_list", String.valueOf(jsonMainNode_hr));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                //告知使用者連線失敗
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
