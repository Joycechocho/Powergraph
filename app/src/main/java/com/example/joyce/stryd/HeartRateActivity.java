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

public class HeartRateActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    private static GraphicalView view2;
    private HRLineGraph line2 = new HRLineGraph();
    int[] HRNumbers;

    JSONArray jsonMainNode_hr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        StartGraph2();

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
                    jsonMainNode_hr = jsonResponse.optJSONArray("heart_rate_list");
                    GetHRData(jsonMainNode_hr);
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

    public void GetHRData(JSONArray jsonMainNode_hr)
    {
        HRNumbers = new int[jsonMainNode_hr.length()];
        for (int i = 0; i < jsonMainNode_hr.length(); i++)
        {
            HRNumbers[i] = jsonMainNode_hr.optInt(i);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Point p = new Point(i, HRNumbers[i]);
            line2.addHeartRatePoints(p);
            view2.repaint();
        }
    }


    public void StartGraph2() {
        view2 = line2.getheartrateview(this);
        setContentView(view2);
    }

}
