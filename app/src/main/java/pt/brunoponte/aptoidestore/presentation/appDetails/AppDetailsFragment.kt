package pt.brunoponte.aptoidestore.presentation.appDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.brunoponte.aptoidestore.R

class AppDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AppDetailsFragment()
    }

    private lateinit var viewModel: AppDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}