package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private Button northButton, southButton, eastButton, westButton, navigateButton;
    private IntentFilter intentFilter;
    private EditText editText;

    private int noOfClicks;

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = editText.getText().toString();
            int id = v.getId();
            String direction = "";
            if (id == R.id.button_NORTH) {
                noOfClicks++;

                direction = "North";

                if(text.equals("")) {
                    text = direction;
                } else {
                    text += ", " + direction;
                }
                editText.setText(text);
            } else if (id == R.id.button_SOUTH) {
                noOfClicks++;

                direction = "South";

                if(text.equals("")) {
                    text = direction;
                } else {
                    text += ", " + direction;
                }
                editText.setText(text);
            } else if (id == R.id.button_EAST) {
                noOfClicks++;

                direction = "East";

                if(text.equals("")) {
                    text = direction;
                } else {
                    text += ", " + direction;
                }
                editText.setText(text);
            } else if (id == R.id.button_WEST) {
                noOfClicks++;

                direction = "West";

                if(text.equals("")) {
                    text = direction;
                } else {
                    text += ", " + direction;
                }
                editText.setText(text);
            } else if (id == R.id.button_NAVIGATE) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
                intent.putExtra(Constants.commands, text);
                startActivityForResult(intent, Constants.RequestCode);
                editText.setText("");
                noOfClicks = 0;
            }

            if (noOfClicks == 4) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
                intent.putExtra(Constants.commands, text);
                getApplicationContext().startService(intent);
            }
        }
    }
    ButtonListener buttonListener = new ButtonListener(); // instantiere clasa listener pentru buton

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.TAG, intent.getStringExtra(Constants.Message));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        noOfClicks = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        editText = (EditText) findViewById(R.id.editText_OUTPUT);

        southButton = (Button) findViewById(R.id.button_SOUTH);
        northButton = (Button) findViewById(R.id.button_NORTH);
        eastButton = (Button) findViewById(R.id.button_EAST);
        westButton = (Button) findViewById(R.id.button_WEST);
        navigateButton = (Button) findViewById(R.id.button_NAVIGATE);

        southButton.setOnClickListener(buttonListener);
        northButton.setOnClickListener(buttonListener);
        eastButton.setOnClickListener(buttonListener);
        westButton.setOnClickListener(buttonListener);
        navigateButton.setOnClickListener(buttonListener);

        if (savedInstanceState != null) {
            noOfClicks = savedInstanceState.getInt(Constants.noOfCllicks);

            Log.d(Constants.TAG, "No of Clicks = " + noOfClicks);
        }
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.Action);
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

        Intent intent = new Intent(this, Colocviu1_1Service.class);
        stopService(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.RequestCode:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "The activity returned with result REGISTERED", Toast.LENGTH_LONG).show();
                else if (resultCode == RESULT_CANCELED)
                    Toast.makeText(this, "The activity returned with result CANCELED ", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

}