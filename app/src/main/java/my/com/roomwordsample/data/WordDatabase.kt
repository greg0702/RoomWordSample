package my.com.roomwordsample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.com.roomwordsample.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object{

        //declare singleton to prevent multiple instance open at once as room instance is expensive to open
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDbInstance(context: Context): WordDatabase{

            val tempInstance = INSTANCE

            //if INSTANCE is not null, return it directly; else, create db instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database").build()

                INSTANCE = instance

                return instance
            }

        }

    }

}