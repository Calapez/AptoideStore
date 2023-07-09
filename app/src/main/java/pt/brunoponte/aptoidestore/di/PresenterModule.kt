package pt.brunoponte.aptoidestore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsContract
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsPresenter

@InstallIn(value = [FragmentComponent::class])
@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindPresenter(impl: AppDetailsPresenter): AppDetailsContract.Presenter

}