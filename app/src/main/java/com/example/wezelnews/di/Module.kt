package com.example.wezelnews.di

import android.app.Application
import androidx.room.Room
import com.example.wezelnews.data.LocalUserMangerImp
import com.example.wezelnews.data.Repositry.RepositryImpl
import com.example.wezelnews.data.local.NewsDao
import com.example.wezelnews.data.local.NewsDatabase
import com.example.wezelnews.data.local.NewsTypeConvertor
import com.example.wezelnews.data.remote.dto.NewsApi
import com.example.wezelnews.domain.Repositry.NewsRepositry
import com.example.wezelnews.domain.app_entry.AppEntryUseCases
import com.example.wezelnews.domain.app_entry.LocalUserManger
import com.example.wezelnews.domain.app_entry.ReadAppEntry
import com.example.wezelnews.domain.app_entry.SaveUserAppEntry
import com.example.wezelnews.domain.news.DeleteArticle
import com.example.wezelnews.domain.news.GetNews
import com.example.wezelnews.domain.news.InsertArticle
import com.example.wezelnews.domain.news.NewsUseCase
import com.example.wezelnews.domain.news.SearchNewsCase
import com.example.wezelnews.domain.news.SelectArticle
import com.example.wezelnews.domain.news.SelectArticles
import com.example.wezelnews.util.Constants.BASE_URL
import com.example.wezelnews.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun providelocalusermanger(application: Application): LocalUserManger =
        LocalUserMangerImp(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveUserAppEntry = SaveUserAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun providesNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepositry(
        newsApi: NewsApi
    ):NewsRepositry=RepositryImpl(newsApi)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepositry: NewsRepositry,
        newsDao:NewsDao
    ): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepositry),
            searchnews = SearchNewsCase(newsRepositry),
            inserarticle = InsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)

        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao=newsDatabase.newsDao


}
