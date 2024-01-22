package com.example.lab7task2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senseMan;
    private Sensor lightSensor;
    private Sensor proximitySensor;
    private Sensor humiditySensor;

    private TextView lightTextView;
    private TextView proximityTextView;
    private TextView humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightTextView = findViewById(R.id.lightTextView);
        proximityTextView = findViewById(R.id.proximityTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        humiditySensor = senseMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        registerSensor(lightSensor, lightTextView, "Light sensor");
        registerSensor(proximitySensor, proximityTextView, "Proximity sensor");
        registerSensor(humiditySensor, humidityTextView, "Relative humidity sensor");
    }

    private void registerSensor(Sensor sensor, TextView textView, String sensorName) {
        if (sensor != null) {
            senseMan.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            textView.setText(sensorName + ": ");
            Toast.makeText(this, sensorName + " found", Toast.LENGTH_SHORT).show();
        } else {
            textView.setText(sensorName + ": Not available");
            Toast.makeText(this, sensorName + " not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightTextView.setText("Light sensor: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximityTextView.setText("Proximity sensor: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humidityTextView.setText("Relative humidity sensor: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMan.unregisterListener(this);
    }
}