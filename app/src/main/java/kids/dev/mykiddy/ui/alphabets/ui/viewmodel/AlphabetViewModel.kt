package kids.dev.mykiddy.ui.alphabets.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.domain.repository.KidzRepository
import kotlinx.coroutines.launch

class AlphabetViewModel(private val alphabetRepository: KidzRepository):ViewModel(), LifecycleObserver {
    private val _alphabetInfoList = mutableStateOf<List<Alphabet>>(emptyList())
    val alphabetList: State<List<Alphabet>> = _alphabetInfoList

     init {
         fetchAlphabetInfoList()
     }

    private fun fetchAlphabetInfoList(){
        viewModelScope.launch {
            try {
                _alphabetInfoList.value = alphabetRepository.fetchAlphabetInfo()
            } catch (e: Exception) {
             println("${e.printStackTrace()}")
            }
        }
    }

    fun fetchWordForSelectedAlphabet(alphabetName: String): Alphabet? {
        return _alphabetInfoList.value.find { it.alphabetName == alphabetName }
    }

    fun fetchAlphabetIconForSelectedAlphabet(alphabetName: String): Alphabet? {
        return _alphabetInfoList.value.find { it.alphabetName == alphabetName }
    }

   /* override suspend fun fetchAlphabetValue(name: String): String? {
        val alphabets = fetchAlphabetInfo()
        val alphabet = alphabets.find { it.alphabetName == name }
        return alphabet?.alphabetVal
    }*/
}