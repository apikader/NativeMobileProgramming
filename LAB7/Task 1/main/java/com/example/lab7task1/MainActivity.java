package com.example.lab7task1;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab7task1.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager senseMan;
    private ListView Iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map Iv to resource Iv
        Iv = findViewById(R.id.listview);

        // Get sensor manager
        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get sensor list and put inside ArrayList
        List<Sensor> sensorList = senseMan.getSensorList(Sensor.TYPE_ALL);

        // Put the sensor list into ListView
        Iv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorList));

        // Your other code can be added here
    }
}
