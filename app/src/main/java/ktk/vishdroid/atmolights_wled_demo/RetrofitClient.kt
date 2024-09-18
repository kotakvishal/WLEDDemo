package ktk.vishdroid.atmolights_wled_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.33/"  // Replace with your WLED's IP address

    val instance: WLEDApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WLEDApi::class.java)
    }
}
