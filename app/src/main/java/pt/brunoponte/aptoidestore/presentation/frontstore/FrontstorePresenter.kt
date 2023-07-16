package pt.brunoponte.aptoidestore.presentation.frontstore

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.useCases.GetAppListUseCase
import javax.inject.Inject


class FrontstorePresenter
@Inject
constructor(
    private val getAppListUseCase: GetAppListUseCase,
    private val dispatcher: CoroutineDispatcher
) : FrontstoreContract.Presenter {

    private var _view: FrontstoreContract.View? = null

    override fun setView(view: FrontstoreContract.View) {
        _view = view
        getApps()
    }

    override fun onMessageClosed() {
        // TODO Keep state of message
    }

    override fun onDestroy() {
        _view = null
    }

    private fun getApps() {
        CoroutineScope(dispatcher).launch {
            _view?.setLoading(true)

            val response = getAppListUseCase.execute()

            when (response) {
                is Response.Success -> _view?.setApps(
                    response.data.map {
                            AppItemUiModel(
                                it.id,
                                it.name,
                                it.rating,
                                it.graphicUrl,
                                it.iconUrl
                            )
                    }
                )
                is Response.Error -> _view?.setMessage(response.exception.message ?: "")
            }

            _view?.setLoading(false)
        }
    }

}