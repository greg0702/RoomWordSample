package my.com.roomwordsample.application

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import my.com.roomwordsample.data.WordDatabase
import my.com.roomwordsample.repository.WordRepository

//used to instantiate one instance of database and repository when needed only
class WordsApplication: Application() {

    //no need to cancel this scope as it will be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    //by lazy is used to create the instances when needed rather than app start
    val database by lazy { WordDatabase.getDbInstance(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

}