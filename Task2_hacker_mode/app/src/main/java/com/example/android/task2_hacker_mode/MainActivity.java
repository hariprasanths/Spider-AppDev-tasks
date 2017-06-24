package com.example.android.task2_hacker_mode;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer mediaPlayer;
    WarningAsynTask warningAsynTask = null;
    Integer timer = 4;
    private SensorManager sensorManager;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= event.sensor.getMaximumRange()) {

                displayWarningMessage("You are far from the sensor");
                if (warningAsynTask != null)
                    warningAsynTask.cancel(true);
                timer = 4;
                displayTimer(timer);
                releaseMediaPlayer();

            } else {
                displayWarningMessage("You are near the sensor");
                warningAsynTask = new WarningAsynTask();
                warningAsynTask.execute();
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void displayWarningMessage(String message) {
        TextView warningTextView = (TextView) findViewById(R.id.warning_textview);
        warningTextView.setText(message);
    }

    public void displayTimer(Integer timeLeft) {
        TextView timerTextview = (TextView) findViewById(R.id.timer_textview);
        timerTextview.setText("Time left till warning : " + timeLeft + " sec");

    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private class WarningAsynTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            for (timer = 4; timer > 0; timer--) {
                try {
                    synchronized (this) {
                        publishProgress(timer);
                        wait(1000);
                    }

                } catch (InterruptedException e) {
                    timer = 4;
                    releaseMediaPlayer();
                    break;
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
            displayTimer(timer);

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (timer == 0) {
                displayTimer(0);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarmtone);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } else releaseMediaPlayer();

        }
    }
}
