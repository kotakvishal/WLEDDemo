package ktk.vishdroid.atmolights_wled_demo

import com.google.gson.annotations.SerializedName

data class WLEDColorUpdate(
    @SerializedName("seg") val segments: List<SegmentUpdate>
)
data class SegmentUpdate(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("col") val colors: List<List<Int>>
)