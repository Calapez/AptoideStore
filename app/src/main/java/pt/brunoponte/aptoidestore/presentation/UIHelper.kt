package pt.brunoponte.aptoidestore.presentation

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class UIHelper {

    companion object {

        fun showSnackbar(view: View, text: String, duration: Int, dismissCallback: () -> Unit) {
            Snackbar.make(view, text, duration).addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    dismissCallback.invoke()
                }

            }).show()
        }

    }

}