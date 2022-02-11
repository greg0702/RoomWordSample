package my.com.roomwordsample.repository

import kotlinx.coroutines.flow.Flow
import my.com.roomwordsample.data.WordDao
import my.com.roomwordsample.model.Word

//pass in wordDao as private to access the DAO instead of the db
class WordRepository(private val wordDao: WordDao) {

    //room operate on separate thread; observed flow will notify observer if data changed
    val getAllWord: Flow<List<Word>> = wordDao.getAllWords()

    //room by default runs suspend queries off main thread, no need implement anything to make sure long running database work is not done off main thread
    suspend fun insertWord(word: Word){ wordDao.insert(word) }

}