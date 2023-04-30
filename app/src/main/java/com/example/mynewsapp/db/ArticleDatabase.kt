package com.example.mynewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase(){

    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile
        private var instance :ArticleDatabase? = null

        fun getDatabase(context: Context):ArticleDatabase{
            val tempInstance = instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance1 = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).build()
                instance = instance1
                return instance1
            }

        }


    }
}