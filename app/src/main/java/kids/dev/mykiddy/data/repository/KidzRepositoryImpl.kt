package kids.dev.mykiddy.data.repository

import kids.dev.mykiddy.data.alphabetinfo.KidzInfo
import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.domain.model.ColorInfo
import kids.dev.mykiddy.domain.repository.KidzRepository

class KidzRepositoryImpl(private val kidzInfo: KidzInfo) : KidzRepository {
    override suspend fun fetchAlphabetInfo(): List<Alphabet> = kidzInfo.getAlphabetDetails()
    override suspend fun fetchColorList(): List<ColorInfo> = kidzInfo.getColorCodeInfo()

}