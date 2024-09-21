package ktk.vishdroid.atmolights_wled_demo
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val myWebView= activityMainBinding.myWebView
        myWebView.webViewClient = WebViewClient() // Ensures links open within the app
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true

        // Change color to red (example)
        activityMainBinding.changeColor.setOnClickListener {
            checkIfConnectedInAPMode(true,this,)
        }


        wledViewModel.openDeviceOrNot.observe(this){
            openDevice->
            if(openDevice){
                Toast.makeText(this,"make request", Toast.LENGTH_SHORT).show()
                val device=DeviceDiscovery.getDefaultAPDevice()
                activityMainBinding.myWebView.loadUrl("http://${device.address}")
            }
        }


    }
    fun checkIfConnectedInAPMode(openDevice: Boolean = false, context: Context) {
        val TAG="DEBUG_ATMOLIGHTS"
        Log.i(TAG, "Checking if connected to AP mode")
        var isConnectedToWledAP: Boolean
        try {
            isConnectedToWledAP = DeviceDiscovery.isConnectedToWledAP(context)
        } catch (e: Exception) {
            isConnectedToWledAP = false
            Log.e(TAG, "Error in checkIfConnectedInAPMode: " + e.message, e)
        }

        if (isConnectedToWledAP) Toast.makeText(context,"Device Conntected",
            Toast.LENGTH_SHORT).show() else Toast.makeText(context,"Device Not Connected", Toast.LENGTH_SHORT).show()

        if (isConnectedToWledAP) {
            Log.i(TAG, "Device is in AP Mode!")

            val connectionManager =
                context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager?

            val request = NetworkRequest.Builder()
            request.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

            connectionManager!!.requestNetwork(
                request.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        try {
                            connectionManager.bindProcessToNetwork(network)
                            if (openDevice) {
                                Toast.makeText(this@MainActivity,"posting live data true",Toast.LENGTH_SHORT).show()
                                wledViewModel.openDeviceOrNot.postValue(true)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
    }
}
