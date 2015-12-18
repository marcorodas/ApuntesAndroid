package area51pe.fragmentmanager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentList fragmentList = new FragmentList();
        final FragmentContent fragmentContent = new FragmentContent();

        fragmentList.setFragmentListInterface(new FragmentList.FragmentListInterface() {
            @Override
            public void onTouch() {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.place_holder, fragmentContent)
                        .addToBackStack(null)
                        .commit();
            }
        });

        fragmentManager
                .beginTransaction()
                .replace(R.id.place_holder, fragmentList)
                .commit();
    }
}
