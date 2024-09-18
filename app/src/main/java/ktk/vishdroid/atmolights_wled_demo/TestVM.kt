package ktk.vishdroid.atmolights_wled_demo
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestVM : ViewModel() {

    private val _colorChangeStatus = MutableLiveData<String>()
    val colorChangeStatus: LiveData<String> get() = _colorChangeStatus

    private val wledApi = RetrofitClient.instance

    fun changeLightColor(hue: Int, saturation: Int, brightness: Int, effect: Int) {
        val colorParams = "$hue,$saturation,$brightness"
        val call = wledApi.changeLightColor(effect, colorParams)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                    _colorChangeStatus.postValue("Color changed successfully")
                } else {
                    _colorChangeStatus.postValue("Failed to change color: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _colorChangeStatus.postValue("Error: ${t.message}")
            }
        })
    }
}
