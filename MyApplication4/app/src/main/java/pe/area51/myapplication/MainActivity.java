package pe.area51.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TIMER_FRECUENCY = 1;

    private TextView displayTxtView;
    private Button toggleTimerBtn;

    private RepetitiveTask repetitiveTask;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayTxtView = (TextView) findViewById(R.id.txtView_display);
        toggleTimerBtn = (Button) findViewById(R.id.btn_toggleTimer);
        toggleTimerBtn.setOnClickListener(this);
        toggleTimerBtn.setText(R.string.start_timer);
        displayTxtView.setText("0");
        repetitiveTask = new RepetitiveTask(TIMER_FRECUENCY, new Runnable() {
            @Override
            public void run() {
                addCount();
                updateDisplay();
            }
        });
    }

    private void resetCounter() {
        this.counter = 0;
    }

    private void addCount() {
        this.counter++;
    }

    private void updateDisplay() {
        displayTxtView.setText(Integer.toString(counter));
    }

    @Override
    public void onClick(View v) {
        if (!repetitiveTask.isRunning()) {
            toggleTimerBtn.setText(R.string.stop_timer);
            repetitiveTask.start(false);
        }else{
            toggleTimerBtn.setText(R.string.start_timer);
            repetitiveTask.stop();
        }
    }
    }
