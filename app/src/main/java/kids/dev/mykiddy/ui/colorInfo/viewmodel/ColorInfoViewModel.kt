package kids.dev.mykiddy.ui.colorInfo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.domain.model.ColorInfo
import kids.dev.mykiddy.domain.repository.KidzRepository
import kotlinx.coroutines.launch

class ColorInfoViewModel(private val alphabetRepository: KidzRepository) : ViewModel(),
    LifecycleObserver {

    private val _colorInfoList = mutableStateOf<List<ColorInfo>>(emptyList())
    val colorInfoList: State<List<ColorInfo>> = _colorInfoList

    init {
        fetchColorInfoList()
    }


    private fun fetchColorInfoList(){
        viewModelScope.launch {
            try {
                _colorInfoList.value = alphabetRepository.fetchColorList()
            } catch (e: Exception) {
                println("${e.printStackTrace()}")
            }
        }
    }

    fun fetchColorNameForSelectedColor(colorCode: String): String? {
        return _colorInfoList.value.find { it.colorCode == colorCode }?.colorName
    }
}