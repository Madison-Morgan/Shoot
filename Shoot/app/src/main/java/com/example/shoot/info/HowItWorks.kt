package com.example.shoot.info

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoot.R

class HowItWorks : Fragment() {

    companion object {
        fun newInstance() = HowItWorks()
    }

    private lateinit var viewModel: HowItWorksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.howitworks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HowItWorksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}