package com.task.reddit.module

import com.task.reddit.data.paging.ArticlePagingSource
import com.task.reddit.data.remote.RetrofitApi
import com.task.reddit.data.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.reddit.com/")
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        pagingSource: ArticlePagingSource
    ): ArticlesRepository {
        return ArticlesRepository(pagingSource)
    }

    @Singleton
    @Provides
    fun providePagingSource(
        api: RetrofitApi
    ): ArticlePagingSource {
        return ArticlePagingSource(api)
    }

}