import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ktk.vishdroid.atmolights_wled_demo.RetrofitClient
import ktk.vishdroid.atmolights_wled_demo.WLEDState
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
                    println("DEBUG::ATMOLIGHTS:Success: $response")
                    _wledState.postValue(response.body())  // Update LiveData with response
                } else {
                    println("DEBUG::ATMOLIGHTS:FAILED: $response")
                    println("Failed to fetch WLED state: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WLEDState>, t: Throwable) {
                println("DEBUG::ATMOLIGHTS:ERROR: $t")
                t.printStackTrace()
            }
        })
    }
}
