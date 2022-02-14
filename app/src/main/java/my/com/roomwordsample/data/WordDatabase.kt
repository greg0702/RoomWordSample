package my.com.roomwordsample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import my.com.roomwordsample.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    //used to populate the database when app start
    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.wordDao()) }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAllWord()

            // Add sample words.
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
            word = Word("I")
            wordDao.insert(word)
            word = Word("Am")
            wordDao.insert(word)
            word = Word("Doing")
            wordDao.insert(word)
            word = Word("Android")
            wordDao.insert(word)
            word = Word("Development!")
            wordDao.insert(word)

        }
    }

    companion object{

        //declare singleton to prevent multiple instance open at once as room instance is expensive to open
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDbInstance(context: Context, scope: CoroutineScope): WordDatabase{

            val tempInstance = INSTANCE

            //if INSTANCE is not null, return it directly; else, create db instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database")
                    .addCallback(WordDatabaseCallback(scope)).build()

                INSTANCE = instance

                return instance
            }

        }
    }

}