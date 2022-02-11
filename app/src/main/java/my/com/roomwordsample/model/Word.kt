package my.com.roomwordsample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table") //create entity
data class Word(

    @PrimaryKey //create primary key; each word is their own primary key
    @ColumnInfo(name = "word") //set name of the column
    val word:String

)