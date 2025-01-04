package com.example.runningmate

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.runningmate.repositories.AchievementRepository
import com.example.runningmate.repositories.AuthenticationRepository
import com.example.runningmate.repositories.ChallengeRepository
import com.example.runningmate.repositories.NetworkAchievementRepository
import com.example.runningmate.repositories.NetworkAuthenticationRepository
import com.example.runningmate.repositories.NetworkChallengeRepository
import com.example.runningmate.repositories.NetworkUserRepository
import com.example.runningmate.repositories.UserRepository
import com.example.runningmate.services.AchievementAPIService
import com.example.runningmate.services.AuthenticationAPIService
import com.example.runningmate.services.ChallengeAPIService
import com.example.todolistapp.services.UserAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authenticationRepository: AuthenticationRepository
    val userRepository: UserRepository
    val achievementRepository: AchievementRepository
    val challengeRepository : ChallengeRepository
}

class DefaultAppContainer(
    private val userDataStore: DataStore<Preferences>,
): AppContainer{
    private val APIbaseURL = "http://192.168.1.15:3000/" //isi pake IP address wifi //address hotspot elvern

    private val authenticationRetrofitService: AuthenticationAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(AuthenticationAPIService::class.java)
    }

    private val achievementretrofitService: AchievementAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(AchievementAPIService::class.java)
    }

    private val challengeretrofitService: ChallengeAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(ChallengeAPIService::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client.build()).baseUrl(APIbaseURL).build()
    }

    private val userRetrofitService: UserAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(UserAPIService::class.java)
    }

    override val authenticationRepository: AuthenticationRepository by lazy {
        NetworkAuthenticationRepository(authenticationRetrofitService)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(userDataStore, userRetrofitService)
    }

    override val achievementRepository: AchievementRepository by lazy {
        NetworkAchievementRepository(achievementretrofitService)
    }

    override val challengeRepository: ChallengeRepository by lazy {
        NetworkChallengeRepository(challengeretrofitService)
    }
}