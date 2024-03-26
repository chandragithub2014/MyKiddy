package kids.dev.mykiddy.data.di


import kids.dev.mykiddy.data.alphabetinfo.KidzInfo
import kids.dev.mykiddy.data.repository.KidzRepositoryImpl
import kids.dev.mykiddy.domain.repository.KidzRepository
import kids.dev.mykiddy.ui.alphabets.ui.viewmodel.AlphabetViewModel
import kids.dev.mykiddy.ui.colorInfo.viewmodel.ColorInfoViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
    single { KidzInfo() }
    single<KidzRepository> { KidzRepositoryImpl(get()) }
    viewModel { AlphabetViewModel(get()) }
    viewModel { ColorInfoViewModel(get()) }
}