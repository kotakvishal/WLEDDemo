package ktk.vishdroid.atmolights_wled_demo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

object CommonUtils{
    fun checkIfConnectedInAPMode(openDevice: Boolean = false,context: Context) {
        val TAG="DEBUG_ATMOLIGHTS"
        Log.i(TAG, "Checking if connected to AP mode")
        var isConnectedToWledAP: Boolean
        try {
            isConnectedToWledAP = DeviceDiscovery.isConnectedToWledAP(context)
        } catch (e: Exception) {
            isConnectedToWledAP = false
            Log.e(TAG, "Error in checkIfConnectedInAPMode: " + e.message, e)
        }

         if (isConnectedToWledAP) Toast.makeText(context,"Device Conntected",Toast.LENGTH_SHORT).show() else Toast.makeText(context,"Device Not Connected",Toast.LENGTH_SHORT).show()

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
                                Toast.makeText(context,"Ready to make default device api call",Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
    }

}