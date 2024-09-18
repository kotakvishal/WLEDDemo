package ktk.vishdroid.atmolights_wled_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestVM : ViewModel() {

    private val _wledState = MutableLiveData<WLEDState>()
    val wledState: LiveData<WLEDState> get() = _wledState

    private val wledApi = RetrofitClient.instance

    fun fetchWLEDState() {
        val call = wledApi.getWLEDState()

        call.enqueue(object : Callback<WLEDState> {
            override fun onResponse(call: Call<WLEDState>, response: Response<WLEDState>) {
                if (response.isSuccessful) {
                    _wledState.postValue(response.body())
                } else {
                    println("Failed to fetch WLED state: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WLEDState>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun setWLEDColor(color: List<List<Int>>) {
        val payload = WLEDColorUpdate(
            segments = listOf(
                SegmentUpdate(
                    colors = color
                )
            )
        )

        println("DEBUG::ATMOLIGHTS::Payload: $payload")

        val call = wledApi.setWLEDColor(payload)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Color update successful")
                } else {
                    println("Failed to update color: ${response.code()}")
                    println("Response Body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
