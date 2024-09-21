package ktk.vishdroid.atmolights_wled_demo

import android.graphics.Color


data class Device(
    val address: String,
    val name: String,
    val isCustomName: Boolean,
    val isHidden: Boolean,
    val macAddress: String,
    val brightness: Int = 0,
    val color: Int = Color.WHITE,
    val isPoweredOn: Boolean = false,
    val isOnline: Boolean = false,
    val isRefreshing: Boolean = false,
    val networkBssid: String = UNKNOWN_VALUE,
    val networkRssi: Int = -101,
    val networkSignal: Int = 0,
    val string: String = UNKNOWN_VALUE
) {

    fun getDeviceUrl(): String {
        return "http://$address"
    }

    companion object {
        const val UNKNOWN_VALUE = "__unknown__"
    }
}