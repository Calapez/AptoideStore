package pt.brunoponte.aptoidestore.presentation.appDetails

import java.text.NumberFormat
import java.text.StringCharacterIterator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AppDetailsUiModel (
    var id: Long,
    var name: String?,
    var size: Long?,
    var downloads: Long?,
    var updated: LocalDateTime?,
    var rating: Float?,
    var graphicUrl: String?,
) {
    fun getUpdatedDateUiString() =
        updated?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))

    fun getSizeUiString() = size?.let { bytes ->
        if (-1000 < bytes && bytes < 1000) {
            return "$bytes B"
        }

        var newValue = bytes
        val characterIterator = StringCharacterIterator("kMGTPE")
        while (newValue <= -999950 || newValue >= 999950) {
            newValue /= 1000
            characterIterator.next()
        }

        String.format("%.1f %cB", newValue / 1000.0, characterIterator.current())
    }

    fun getDownloadsUiString() = downloads?.let {
        NumberFormat.getIntegerInstance().format(downloads);
    }
}