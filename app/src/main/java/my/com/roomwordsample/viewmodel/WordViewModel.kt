package my.com.roomwordsample.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import my.com.roomwordsample.model.Word
import my.com.roomwordsample.repository.WordRepository

class WordViewModel(private val repository: WordRepository): ViewModel() {

    //use livedata can put observer on data and only update UI when data changes; repository is also completely separated from UI through viewmodel
    val allWords: LiveData<List<Word>> = repository.getAllWord.asLiveData()

    //launch the insert function in coroutine
    fun insertWord(word: Word){
        viewModelScope.launch { repository.insertWord(word) }
    }

}

//function that gets dependencies needed to create viewmodel
class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}