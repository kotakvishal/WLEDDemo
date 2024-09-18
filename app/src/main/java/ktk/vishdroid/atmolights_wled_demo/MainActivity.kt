package ktk.vishdroid.atmolights_wled_demo

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
    private lateinit var viewModel: TestVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[TestVM::class.java]





        // Observe the color change status
        viewModel.colorChangeStatus.observe(this, Observer { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        // Example usage: Change WLED color to red with a specific effect
        viewModel.changeLightColor(hue = 0, saturation = 100, brightness = 255, effect = 15)




        viewModel.colorChangeStatus.observe(this){
            colorStateus->

            Toast.makeText(this,colorStateus,Toast.LENGTH_SHORT).show()
        }
    }


}