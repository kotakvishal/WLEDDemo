package ktk.vishdroid.atmolights_wled_demo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WLEDApi {
    @GET("json/state")
    fun getWLEDState(): Call<WLEDState>  // JSON response as WLEDState
}