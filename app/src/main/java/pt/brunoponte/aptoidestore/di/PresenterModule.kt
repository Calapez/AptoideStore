package pt.brunoponte.aptoidestore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsContract
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsPresenter
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstoreContract
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstorePresenter

@InstallIn(value = [FragmentComponent::class])
@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindFrontstorePresenter(impl: FrontstorePresenter): FrontstoreContract.Presenter

    @Binds
    abstract fun bindAppDetailsPresenter(impl: AppDetailsPresenter): AppDetailsContract.Presenter

}