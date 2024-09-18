package ktk.vishdroid.atmolights_wled_demo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WLEDApi {
    @GET("json/state")
    fun getWLEDState(): Call<WLEDState>  // JSON response as WLEDState

    @POST("json/state")
    fun setWLEDColor(@Body payload: WLEDColorUpdate): Call<Void>
}
