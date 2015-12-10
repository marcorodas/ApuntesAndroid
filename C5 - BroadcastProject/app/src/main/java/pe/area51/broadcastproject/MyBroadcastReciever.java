package pe.area51.broadcastproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by alumno on 12/1/15.
 */
public class MyBroadcastReciever extends BroadcastReceiver {

    //Captura eventos
    //Context:  contexto de ejecucuion del sistema operativo,
    //          puede iniciar servicios
    //Intent: (sistema de mensajería de android, intención)
    //          información disparada desde el sistema operativo
    @Override
    public void onReceive(Context context, Intent intent) {
        String mensaje = "N/A";
        if (intent.getAction() !=null){
            switch (intent.getAction()) {
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    mensaje = "Cambio en el modo avion";
                    break;
                case LocationManager.PROVIDERS_CHANGED_ACTION:
                    mensaje = "Cambio en el estado del proveedor de localización";
                    break;
                case MainActivity.MY_CUSTOM_BROADCAST:
                    mensaje = "Intent implicito";
                    break;
            }
        }else{
            mensaje = "Intent explicito";
        }
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }
}
