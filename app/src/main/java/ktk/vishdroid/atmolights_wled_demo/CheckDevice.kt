package ktk.vishdroid.atmolights_wled_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ktk.vishdroid.atmolights_wled_demo.databinding.ActivityCheckDeviceBinding
import ktk.vishdroid.atmolights_wled_demo.databinding.ActivityMainBinding

class CheckDevice : AppCompatActivity() {
    lateinit var checkDeviceBinding:ActivityCheckDeviceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkDeviceBinding = ActivityCheckDeviceBinding.inflate(layoutInflater)
        setContentView(checkDeviceBinding.root)
        checkDeviceBinding.checkDeviceConnection.setOnClickListener {
            CommonUtils.checkIfConnectedInAPMode(true,this)
        }
    }
}