package com.example.joyce.stryd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.achartengine.GraphicalView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PowerActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    private static GraphicalView view;
    private PowerLineGraph line = new PowerLineGraph();

    int[] PowerData;

    JSONArray jsonMainNode_power;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        StartGraph();

        Request request = new Request.Builder().url("https://www.stryd.com/b/interview/data").build();
        Call call = client.newCall(request);

        call.enqueue(new Callback()
        {
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                JSONObject jsonResponse;
                try
                {
                    String json = response.body().string();
                    jsonResponse = new JSONObject(json);
                    jsonMainNode_power = jsonResponse.optJSONArray("total_power_list");
                    GetPowerData(jsonMainNode_power);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error", "Connection Fail");
            }

        });
    }

    public void GetPowerData(JSONArray jsonMainNode_power)
    {
        PowerData = new int[jsonMainNode_power.length()];
        for (int i = 0; i < jsonMainNode_power.length(); i++)
        {
            PowerData[i] = jsonMainNode_power.optInt(i);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Point p = new Point(i, PowerData[i]);
            line.addPowerPoints(p);
            view.repaint();
        }
    }

    public void StartGraph() {
        view = line.getpowerview(this);
        setContentView(view);
    }


}
