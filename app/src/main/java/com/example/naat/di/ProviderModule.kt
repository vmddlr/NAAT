package com.example.naat.di

import android.content.Context
import androidx.room.Room
import com.example.naat.data.network.api.LoginApi
import com.example.naat.data.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Singleton
    @Provides
    fun providesInterceptor() = Interceptor {chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization","Basic  Wm1Ga0xXTXlZeTF3YjNKMFlXdz06TWpoa04yUTNNbUppWVRWbVpHTTBObVl4Wmpka1lXSmpZbVEyTmpBMVpEVXpaVFZoT1dNMVpHVTROakF4TldVeE9EWmtaV0ZpTnpNd1lUUm1ZelV5WWc9PQ==")
            .url(url)
            .build()

        chain.proceed(newRequest)
    }


    @Singleton
    @Provides
    fun providesHttpInterceptor() = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesClient(networkInterceptor: HttpLoggingInterceptor, interceptor: Interceptor) = OkHttpClient
        .Builder()
        .addNetworkInterceptor(networkInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient) =
        Retrofit
            .Builder()
            .baseUrl("https://uat.firmaautografa.com/authorization-server/oauth/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesUserService(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    //  Room
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "APP_DataBase"
        ).build()


    @Singleton
    @Provides
    fun providerDao(db: AppDataBase) =
        db.appDao()
}