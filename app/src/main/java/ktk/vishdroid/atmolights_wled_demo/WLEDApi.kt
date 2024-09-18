package ktk.vishdroid.atmolights_wled_demo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WLEDApi {
    @GET("win")
    fun changeLightColor(
        @Query("FX") effect: Int,
        @Query("HSB") color: String
    ): Call<Void>
}
