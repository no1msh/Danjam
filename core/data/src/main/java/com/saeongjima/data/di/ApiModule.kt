package com.saeongjima.data.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saeongjima.data.BuildConfig
import com.saeongjima.data.DanjamCallAdapter
import com.saeongjima.data.token.AccessTokenInterceptor
import com.saeongjima.data.token.DefaultAuthenticator
import com.saeongjima.data.token.NeedAuthClient
import com.saeongjima.data.token.NeedAuthRetrofit
import com.saeongjima.data.token.PublicClient
import com.saeongjima.data.token.PublicRetrofit
import com.saeongjima.data.token.RefreshTokenClient
import com.saeongjima.data.token.RefreshTokenInterceptor
import com.saeongjima.data.token.SignInClient
import com.saeongjima.data.token.SignInInterceptor
import com.saeongjima.data.token.SignInRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val CONTENT_TYPE = "application/json"
    private const val CONNECT_TIMEOUT = 20L
    private const val WRITE_TIMEOUT = 20L
    private const val READ_TIMEOUT = 20L
    private const val TAG_HTTP_LOG = "Http_Log"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Log.d(TAG_HTTP_LOG, message)
        }.apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Provides
    @Singleton
    @PublicClient
    fun providePublicOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @PublicRetrofit
    fun providePublicRetrofit(@PublicClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .addCallAdapterFactory(DanjamCallAdapter.CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @SignInClient
    fun provideSignInOkHttpClient(
        signInInterceptor: SignInInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(signInInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @SignInRetrofit
    fun provideSignInRetrofit(@SignInClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .addCallAdapterFactory(DanjamCallAdapter.CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @NeedAuthClient
    fun provideAuthOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
        authenticator: DefaultAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(accessTokenInterceptor)
            .authenticator(authenticator)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @NeedAuthRetrofit
    fun provideAuthRetrofit(
        @NeedAuthClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(DanjamCallAdapter.CallAdapterFactory)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @RefreshTokenClient
    fun provideRefreshTokenOkHttpClient(
        refreshTokenInterceptor: RefreshTokenInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}
