package pt.brunoponte.aptoidestore.presentation.appDetails

interface AppDetailsContract {

    interface View {
        fun setLoading(enabled: Boolean)
        fun setApp(app: AppDetailsUiModel?)
        fun setMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun setAppId(appId: Long)
        fun onMessageClosed()
        fun onDestroy()
    }
}