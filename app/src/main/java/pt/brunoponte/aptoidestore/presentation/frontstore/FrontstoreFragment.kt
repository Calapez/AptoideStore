package pt.brunoponte.aptoidestore.presentation.frontstore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.brunoponte.aptoidestore.R

class FrontstoreFragment : Fragment() {

    companion object {
        fun newInstance() = FrontstoreFragment()
    }

    private lateinit var viewModel: FrontstoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frontstore, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FrontstoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}