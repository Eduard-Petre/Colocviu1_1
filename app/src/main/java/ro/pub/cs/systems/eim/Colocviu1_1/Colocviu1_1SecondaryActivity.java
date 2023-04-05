package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {

    private EditText editTextCommands;
    private Button button_OK, button_CANCEL;

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Log.d("Button ID", String.valueOf(id));
            if (id == R.id.button_OK) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            } else if (id == R.id.button_CANCEL) {
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
            }
        }
    }
    ButtonListener buttonListener = new ButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);

        editTextCommands = (EditText) findViewById(R.id.editText_Commands);
        button_OK = (Button) findViewById(R.id.button_OK);
        button_CANCEL = (Button) findViewById(R.id.button_CANCEL);

        Intent intent = getIntent();
        if(intent != null) {
            String commands = intent.getStringExtra(Constants.commands);
            editTextCommands.setText(commands);
        }

        button_OK.setOnClickListener(buttonListener);
        button_CANCEL.setOnClickListener(buttonListener);
    }
}