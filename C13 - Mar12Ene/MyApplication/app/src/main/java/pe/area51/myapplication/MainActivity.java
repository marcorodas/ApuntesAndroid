package pe.area51.myapplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {

    private LocationManager locationManager;
    private boolean isRetrievingLocation;
    private TextView txtLocationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isRetrievingLocation = false;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        findViewById(R.id.btn_toggle_location_retrieving).setOnClickListener(this);
        txtLocationInfo = (TextView) findViewById(R.id.txtview_location_info);
    }

    @Override
    public void onClick(View v) {
        if (isRetrievingLocation) {
            stopLocationRetrieving();
        } else {
            startLocationRetrieving();
        }
        //locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }

    private void startLocationRetrieving() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        isRetrievingLocation = true;
    }

    private void stopLocationRetrieving() {
        locationManager.removeUpdates(this);
        isRetrievingLocation = false;
    }

    private void showLocation(final Location location) {
        final String latitude = getString(R.string.latitude,location.getLatitude());
        final String longitude = getString(R.string.longitude,location.getLongitude());
        final String altitude = getString(R.string.altitude,location.getAltitude());
        final String accuracy = getString(R.string.accuracy,location.getAccuracy());
        final String provider = getString(R.string.provider,location.getProvider());
        final String timestamp = getString(R.string.timestamp,location.getTime());
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(latitude + "\n")
                .append(longitude + "\n")
                .append(altitude + "\n")
                .append(accuracy + "\n")
                .append(provider + "\n")
                .append(timestamp);

        txtLocationInfo.setText(stringBuilder.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
