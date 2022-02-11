package my.com.roomwordsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import my.com.roomwordsample.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC") //all word are selected and order alphabetically
    fun getAllWords(): Flow<List<Word>> //flow produces value one at a time that can generate values from async operations (ie network request)

    @Insert(onConflict = OnConflictStrategy.IGNORE) //if the new word is already exist, the new word is ignore and not added
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table") //delete all words
    suspend fun deleteAllWord()

}