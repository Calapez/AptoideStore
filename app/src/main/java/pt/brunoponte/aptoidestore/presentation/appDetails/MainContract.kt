package pt.brunoponte.aptoidestore.presentation.appDetails

interface AppDetailsContract {

    interface View {
        fun showLoadingState()
        fun showContentState(app: AppDetailsUiModel)
        fun showErrorState(errorMsg: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun setAppId(appId: Long)
        fun onDestroy()
    }
}