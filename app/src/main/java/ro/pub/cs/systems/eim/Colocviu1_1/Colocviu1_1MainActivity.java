package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private Button northButton, southButton, eastButton, westButton, navigateButton;
    private EditText editText;

    private int noOfClicks = 0;
    // creare clasa ascultator pentru butonul curent
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            noOfClicks++;
            int id = v.getId();
            String direction = "";
            if (id == R.id.button_NORTH) {
                direction = "NORTH";
            } else if (id == R.id.button_SOUTH) {
                direction = "SOUTH";
            } else if (id == R.id.button_EAST) {
                direction = "EAST";
            } else if (id == R.id.button_WEST) {
                direction = "WEST";
            }

            String text = editText.getText().toString();

            if(text.equals("")) {
                text = direction;
            } else {
                text += ", " + direction;
            }

            editText.setText(text);
        }
    }
    ButtonListener buttonListener = new ButtonListener(); // instantiere clasa listener pentru buton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        editText = (EditText) findViewById(R.id.editText_OUTPUT);

        southButton = (Button) findViewById(R.id.button_SOUTH);
        northButton = (Button) findViewById(R.id.button_NORTH);
        eastButton = (Button) findViewById(R.id.button_EAST);
        westButton = (Button) findViewById(R.id.button_WEST);

        southButton.setOnClickListener(buttonListener);
        northButton.setOnClickListener(buttonListener);
        eastButton.setOnClickListener(buttonListener);
        westButton.setOnClickListener(buttonListener);

        if (savedInstanceState != null) {
            noOfClicks = savedInstanceState.getInt(Constants.noOfCllicks);

            Log.d(Constants.TAG, "No of Clicks = " + noOfClicks);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            savedInstanceState.putInt(Constants.noOfCllicks, noOfClicks);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() has been called.");
    }
}