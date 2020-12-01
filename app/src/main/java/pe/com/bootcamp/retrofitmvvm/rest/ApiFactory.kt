package pe.com.bootcamp.retrofitmvvm.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pe.com.bootcamp.retrofitmvvm.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiFactory {


    private var servicesApiInterface: ApiService? = null

    private val ceClient = OkHttpClient().newBuilder()
        //.addInterceptor(authInterceptor)
        .readTimeout(Constants.REST_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Constants.REST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.REST_TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun build(): ApiService? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .client(ceClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        /*vval httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor { chain ->

                al newRequest = chain.request().newBuilder()
                    .addHeader(
                        Constants.authentication,
                        AppPreferences.bearerToken!!
                    )
                    .build()
                chain.proceed(newRequest)
            }



        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ApiService::class.java
        )*/

        return servicesApiInterface as ApiService
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

}