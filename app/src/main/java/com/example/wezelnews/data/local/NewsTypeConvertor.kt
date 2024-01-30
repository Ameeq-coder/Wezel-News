package com.example.wezelnews.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.wezelnews.domain.model.Source
import dagger.Provides


@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source):String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringtosource(source: String):Source{
        return source.split(",").let { sourceArray->
Source(sourceArray[0],sourceArray[1])
        }
    }


}