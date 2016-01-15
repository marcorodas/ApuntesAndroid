package com.example.alarmas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView timeInSecondsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeInSecondsTextView = (TextView) findViewById(R.id.edittext_time);
        findViewById(R.id.btn_register_alarm).setOnClickListener(this);
    }

    private void registerAlarm(final int timeInSeconds){
        final PendingIntent operation = PendingIntent.getActivity(
                this,
                0,
                new Intent(this,MessageActivity.class),
                PendingIntent.FLAG_ONE_SHOT
        );
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final long timeToBeLaunched = SystemClock.elapsedRealtime()+timeInSeconds*1000;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    timeToBeLaunched,
                    operation
            );
        }
        else{
            alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    timeToBeLaunched,
                    operation);
        }
    }

    @Override
    public void onClick(View v) {
        try{
            final int timeInSeconds = Integer.decode(timeInSecondsTextView.getText().toString());
            registerAlarm(timeInSeconds);
        }
        catch (NumberFormatException e){
            Toast.makeText(MainActivity.this,"Ingrese un numero valido", Toast.LENGTH_SHORT);
        }
    }
}
