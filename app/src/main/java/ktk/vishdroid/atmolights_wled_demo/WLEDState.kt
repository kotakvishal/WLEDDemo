package ktk.vishdroid.atmolights_wled_demo

data class WLEDState(
    val on: Boolean,        // Light on or off
    val bri: Int,           // Brightness level
    val seg: List<Segment>  // List of segments
)