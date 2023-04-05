package ro.pub.cs.systems.eim.Colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {

    private Context context;
    private String commands;

    public ProcessingThread(Context context, String commmands) {
        this.commands = commmands;
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("Call", "Thread has started!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Intent intent = new Intent();
        intent.setAction(Constants.Action);
        intent.putExtra(Constants.Message, commands);

        context.sendBroadcast(intent);
    }
}
