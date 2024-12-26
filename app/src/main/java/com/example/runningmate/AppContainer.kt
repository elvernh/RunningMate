package com.example.runningmate

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.runningmate.repositories.AuthenticationRepository
import com.example.runningmate.repositories.NetworkAuthenticationRepository
import com.example.runningmate.services.AuthenticationAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authenticationRepository: AuthenticationRepository
}

class DefaultAppContainer(
    private val userDataStore: DataStore<Preferences>
): AppContainer{
    private val APIbaseURL = "http://192.168.1.9:3000/" //isi pake IP address wifi //address hotspot elvern

    private val authenticationRetrofitService: AuthenticationAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(AuthenticationAPIService::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client.build()).baseUrl(APIbaseURL).build()
    }
    override val authenticationRepository: AuthenticationRepository by lazy {
        NetworkAuthenticationRepository(authenticationRetrofitService)
    }
}