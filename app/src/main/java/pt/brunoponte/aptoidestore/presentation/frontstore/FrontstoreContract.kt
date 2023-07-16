package pt.brunoponte.aptoidestore.presentation.frontstore

interface FrontstoreContract {

    interface View {
        fun setLoading(enabled: Boolean)
        fun setApps(apps: List<AppItemUiModel>?)
        fun setMessage(message: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun onMessageClosed()
        fun onDestroy()
    }
}