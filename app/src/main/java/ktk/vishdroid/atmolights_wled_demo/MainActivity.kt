package ktk.vishdroid.atmolights_wled_demo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ktk.vishdroid.atmolights_wled_demo.databinding.ActivityMainBinding
import kotlin.random.Random

class   MainActivity : AppCompatActivity() {
    private lateinit var wledViewModel: TestVM
    private lateinit var  activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        wledViewModel = ViewModelProvider(this)[TestVM::class.java]

        wledViewModel.wledState.observe(this, Observer { state ->
            state?.let {
                println( "DEBUG::ATMOLIGHTS::WLED is ${if (it.on) "ON" else "OFF"}, Brightness: ${it.bri}")
                println( "DEBUG::ATMOLIGHTS: ${it.seg[0].fx}")
            }
        })

        // Fetch the state when the activity is created
        wledViewModel.fetchWLEDState()

        // Change color to red (example)
        activityMainBinding.changeColor.setOnClickListener {
//            wledViewModel.setWLEDColor(generateRandomColor())
            startActivity(Intent(this,CheckDevice::class.java))
        }
    }
    fun generateRandomColor(): List<List<Int>> {
        // Generate random values for Red, Green, and Blue components
        val red = Random.nextInt(0, 256) // Random value between 0 and 255
        val green = Random.nextInt(0, 256) // Random value between 0 and 255
        val blue = Random.nextInt(0, 256) // Random value between 0 and 255

        // Return the color in the required format
        return listOf(listOf(red, green, blue))
    }
}
