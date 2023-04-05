package ro.pub.cs.systems.eim.Colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;

public class Colocviu1_1Service extends Service {
    private int startMode;
    private ProcessingThread processingThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,
                              int flags,
                              int startId) {
        String commands = new Date(System.currentTimeMillis()) + " - " + intent.getStringExtra(Constants.commands);

        Log.d(Constants.TAG, "Service has been called");
        processingThread = new ProcessingThread(this, commands);
        processingThread.start();

        return startMode;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Service has been destroyed");
    }

}
