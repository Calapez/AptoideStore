package pt.brunoponte.aptoidestore.presentation.appDetails

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.useCases.GetAppUseCase
import javax.inject.Inject


class AppDetailsPresenter
@Inject
constructor(
    private val getAppUseCase: GetAppUseCase,
    private val dispatcher: CoroutineDispatcher
) : AppDetailsContract.Presenter {

    private var view: AppDetailsContract.View? = null

    override fun setView(view: AppDetailsContract.View) {
        this.view = view
    }

    override fun setAppId(appId: Long) {
        CoroutineScope(dispatcher).launch {
            view?.setLoading(true)

            val response = getAppUseCase.execute(appId)

            when (response) {
                is Response.Success -> view?.setApp(
                    AppDetailsUiModel(
                        response.data.id,
                        response.data.name,
                        response.data.size,
                        response.data.downloads,
                        response.data.updated,
                        response.data.rating,
                        response.data.graphicUrl
                    )
                )
                is Response.Error -> view?.setMessage(response.exception.message ?: "")
            }

            view?.setLoading(false)
        }
    }

    override fun onMessageClosed() {
        // TODO Keep state of message
    }

    override fun onDestroy() {
        view = null
    }

}