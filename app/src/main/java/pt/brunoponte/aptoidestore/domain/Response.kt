package pt.brunoponte.aptoidestore.domain

/**
 * Represents a response from Data layer, which is transmitted to the Presentation layer
 */
sealed class Response<out T> {

    data class Error(val exception: Exception) : Response<Nothing>()

    data class Success<T>(val data: T) : Response<T>()

}