package pe.area51.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_NAME = "pe.area51.myapplication.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_say_hello)
                .setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_say_hello:
                EditText nameEditText = (EditText) findViewById(R.id.edit_txt_name);
                String name = extractText(nameEditText);
                showWelcome(name);
                break;
        }
    }

    private void showWelcome(String name) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(EXTRA_NAME,name);
        startActivity(intent);
    }

    private String extractText(EditText editText) {
        return editText.getText().toString();
    }

}
