package com.example.alumno.myapplication;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by alumno on 12/3/15.
 */
public class MyService extends Service {

    public static final int MY_SERVICE_FOREGROUND_ID = 1;
    public boolean isRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Notification myServiceNotification =
                new Notification.Builder(this)
                .setContentTitle("MyService")
                .getNotification();
        startForeground(MY_SERVICE_FOREGROUND_ID,myServiceNotification);
        //Main invocation code goes here
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //executelongTask();
        if(!isRunning) {
            Toast.makeText(this, "1ra vez", Toast.LENGTH_SHORT).show();
            isRunning = true;
        }else{
            Toast.makeText(this, "El servicio ya se esta ejecutando", Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY; //Lo que hace el SO si este lo mata
        //START_NOT_STICKY: No hacer nada
        //START_STICKY: El SO vuelva a lanzar el servicio si lo ha matado.
        //START_REDELIVER_INTENT: Como el START_STICKY y recupera el intent en el parámetro.
        // el intent contendra los parametros que se le adjuntaron
    }

    private void executelongTask(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
