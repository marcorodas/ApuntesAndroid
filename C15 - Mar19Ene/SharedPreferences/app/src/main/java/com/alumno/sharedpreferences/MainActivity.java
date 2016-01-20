package com.alumno.sharedpreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PREFERENCES_NAME = "last_visitor";
    private static final String KEY_LAST_VISITOR = "last_visitor";
    private EditText editTextVisitorName;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextVisitorName = (EditText) findViewById(R.id.edit_txt_visitor_name);
        textViewMessage = (TextView) findViewById(R.id.txt_message);
        showLastVisitor();
        findViewById(R.id.btn_register_visitor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String name = editTextVisitorName.getText().toString();
        showWelcome(name);
        saveLastVisitorName(name);
    }

    private boolean saveLastVisitorName(String name){
        return getSharedPreferences()
                .edit()
                .putString(KEY_LAST_VISITOR,name)
                .commit(); //Lo hace en el mismo hilo
                //apply(); Lo hace de forma asincrona, en otro hilo
    }

    private String loadLastVisitorName(){
         return getSharedPreferences().getString(KEY_LAST_VISITOR,null);
    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
    }

    private void showLastVisitor(){
        //Se deberia hacer en otro hilo
        String lastVisitorName = loadLastVisitorName();
        lastVisitorName = (lastVisitorName == null) ?
                getString(R.string.first_visitor) :
                getString(R.string.last_visitor,lastVisitorName);
        textViewMessage.setText(lastVisitorName);
    }

    private void showWelcome(String name) {
        textViewMessage.setText(getString(R.string.greetings,name));
    }

}
