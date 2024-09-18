package ktk.vishdroid.atmolights_wled_demo

import TestVM
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

class   MainActivity : AppCompatActivity() {
    private lateinit var wledViewModel: TestVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wledViewModel = ViewModelProvider(this)[TestVM::class.java]


        // Observe WLED state response
        wledViewModel.wledState.observe(this, Observer { state ->
            state?.let {
                println( "DEBUG::ATMOLIGHTS::WLED is ${if (it.on) "ON" else "OFF"}, Brightness: ${it.bri}")
                println( "DEBUG::ATMOLIGHTS: ${it.seg[0].fx}")
            }
        })

        // Fetch the state when the activity is created
        wledViewModel.fetchWLEDState()
    }
}