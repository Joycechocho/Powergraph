package com.example.joyce.stryd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button StartPower = (Button) findViewById(R.id.StartPower);
        Button StartHR = (Button) findViewById(R.id.StartHR);

        StartPower.setOnClickListener(this);
        StartHR.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.StartPower:
                Intent power_intent = new Intent();
                power_intent.setClass(MainActivity.this , PowerActivity.class);
                startActivity(power_intent);
                break;
            case R.id.StartHR:
                Intent hr_intent = new Intent();
                hr_intent.setClass(MainActivity.this , HeartRateActivity.class);
                startActivity(hr_intent);
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
