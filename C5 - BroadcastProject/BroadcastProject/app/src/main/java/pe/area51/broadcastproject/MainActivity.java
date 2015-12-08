package pe.area51.broadcastproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String MY_CUSTOM_BROADCAST = "pe.area51.broadcastapp.MY_CUSTOM_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_explicit_broadcast).setOnClickListener(this);
        findViewById(R.id.button_implicit_broadcast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_explicit_broadcast:
                Intent myIntent = new Intent(this,MyBroadcastReciever.class);
                sendBroadcast(myIntent);
                break;
            case R.id.button_implicit_broadcast:
                sendBroadcast(new Intent(MY_CUSTOM_BROADCAST));
                break;
        }
    }
}
