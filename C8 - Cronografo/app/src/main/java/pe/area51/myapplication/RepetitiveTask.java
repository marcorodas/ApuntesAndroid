package pe.area51.myapplication;

import android.os.Handler;

/**
 * Created by alumno on 12/15/15.
 */
public class RepetitiveTask {

    private final int frecuencyInMillis;
    private final Handler handler;
    private final Runnable internalRunnable;

    private boolean isRunning;

    public RepetitiveTask(final int frecuencyInMillis,final Runnable task){
        this.frecuencyInMillis = frecuencyInMillis;
        this.isRunning = false;
        this.handler = new Handler(); //Actua sobre el Hilo actual
        this.internalRunnable = new Runnable() {
            @Override
            public void run() {
                task.run();
                handler.postDelayed(this, frecuencyInMillis);
            }
        };
    }

    public boolean start(){
        return start(true);
    }

    public boolean start(final boolean executeImmediately){
        if(!isRunning){
            isRunning = true;
            if(executeImmediately){
                handler.post(internalRunnable); //Ejecuta inmediatamente
            }else{
                handler.postDelayed(internalRunnable, frecuencyInMillis); //Similar a setTimeOut de js
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean stop(){
        if(isRunning){
            isRunning = false;
            handler.removeCallbacks(internalRunnable);
            return true;
        }else{
            return false;
        }
    }

    public boolean isRunning(){
        return isRunning;
    }
}
