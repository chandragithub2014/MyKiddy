package kids.dev.mykiddy.domain.repository

import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.domain.model.ColorInfo

interface  KidzRepository {
    suspend fun fetchAlphabetInfo(): List<Alphabet>
    suspend fun fetchColorList() : List<ColorInfo>
   // suspend fun fetchAlphabetValue(name: String): String?
}