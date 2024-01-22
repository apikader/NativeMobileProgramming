package com.example.lab7task3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    private Sensor magnetometerSensor;

    private TextView xTextView, yTextView, zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (accelerometerSensor == null || gyroscopeSensor == null || magnetometerSensor == null) {
            Toast.makeText(this, "One or more sensors not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                updateTextViews(x, y, z, "Accelerometer");
                break;
            case Sensor.TYPE_GYROSCOPE:
                updateTextViews(x, y, z, "Gyroscope");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                updateTextViews(x, y, z, "Magnetometer");
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for this example
    }

    private void updateTextViews(float x, float y, float z, String sensorType) {
        String values = String.format("%s\nX: %.2f\nY: %.2f\nZ: %.2f", sensorType, x, y, z);
        switch (sensorType) {
            case "Accelerometer":
                xTextView.setText(values);
                break;
            case "Gyroscope":
                yTextView.setText(values);
                break;
            case "Magnetometer":
                zTextView.setText(values);
                break;
        }
    }
}
