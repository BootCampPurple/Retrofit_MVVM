package pe.com.bootcamp.retrofitmvvm.rest


import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import pe.com.bootcamp.retrofitmvvm.model.ErrorObjectResponse
import pe.com.bootcamp.retrofitmvvm.model.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.util.AppUtils
import pe.com.bootcamp.retrofitmvvm.util.Constants
import retrofit2.Response
import java.io.IOException


open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {

        return safeApiResult(call)

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Result<T> {

        try {
            val response = call.invoke()


            Log.i(Constants.GENERAL_LOG_APP_TAG, response.raw().toString())
            Log.i(Constants.GENERAL_LOG_APP_TAG, response.message())
            Log.i(Constants.GENERAL_LOG_APP_TAG, response.body().toString())
            Log.i(Constants.GENERAL_LOG_APP_TAG, response.raw().networkResponse.toString())

            if (response.isSuccessful) return Result.Success(response.body()!!)


            var networkMessage = NetworkMessage("", 0)

            response.errorBody()?.let {

                val jsonObject = JSONObject(it.string())

                val message: ErrorObjectResponse = AppUtils.fromJson(jsonObject.toString())


                if (message.exceptionMessage.isNotEmpty()) {
                    networkMessage =
                        NetworkMessage(message.exceptionMessage, response.raw().code)
                }
            }

            return Result.ApiError(networkMessage)


        } catch (e: IOException) {

            return Result.NetworkError(e)
        } catch (e: JSONException) {
            return Result.ApiError(NetworkMessage("", 0))
        }
    }


}

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    /**
     * Failure response with body
     */
    data class ApiError(val exception: NetworkMessage) : Result<Nothing>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : Result<Nothing>()

    /**
     * For example, json parsing error
     */
    //data class UnknownError(val error: Throwable?) : Result<Nothing>()
}