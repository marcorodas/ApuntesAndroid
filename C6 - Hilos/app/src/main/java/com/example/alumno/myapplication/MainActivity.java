package com.example.alumno.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_ui_thread).setOnClickListener(this);
        findViewById(R.id.button_worker_thread).setOnClickListener(this);
        findViewById(R.id.button_async_thread).setOnClickListener(this);
        findViewById(R.id.button_intent_thread).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ui_thread:
                runOnUiThread();
                break;
            case R.id.button_worker_thread:
                runOnWorkerThread();
                break;
            case R.id.button_async_thread:
                runOnAsyncThread();
                break;
            case R.id.button_intent_thread:
                break;
        }
    }

    private ProgressDialog createProgressDialog(String message){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setTitle("Cargando...");
        pDialog.setMessage(message);
        pDialog.setIndeterminate(true); //Estilo progreso no determinado
        pDialog.setCancelable(false);
        return pDialog;
    }

    private void runOnUiThread(){
        ProgressDialog pDialog = createProgressDialog("runOnUiThread: Un momento");
        pDialog.show();

        longTask();

        pDialog.dismiss(); //Desaparecer el cuadro
    }

    private void runOnWorkerThread(){
        final ProgressDialog pDialog = createProgressDialog("runOnWorkerThread: Un momento");
        pDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                longTask();
                pDialog.dismiss();
            }
        }).start();
    }

    private void runOnAsyncThread(){
        new AsyncTask<Void,Void,Void>(){
            private ProgressDialog pDialog;

            @Override
            protected void onPreExecute() {
                pDialog = createProgressDialog("runOnAsyncThread: Un momento");
                pDialog.show();
            }

            @Override
            protected Void doInBackground(Void[] params) {
                longTask();
                return null;
            }

            @Override
            protected void onPostExecute(Void o) {
                pDialog.dismiss();
            }
        }.execute();
    }

    private void longTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

