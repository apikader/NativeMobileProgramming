import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab7task1.R

class MainActivity : AppCompatActivity() {

    private lateinit var senseMan: SensorManager
    private lateinit var Iv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Map Iv to resource Iv
        Iv = findViewById(R.id.listview)

        // Get sensor manager
        senseMan = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Get sensor list and put inside ArrayList
        val sensorList: List<Sensor> = senseMan.getSensorList(Sensor.TYPE_ALL)

        // Put the sensor list into ListView
        Iv.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorList)

        // Your other code can be added here
    }
}
