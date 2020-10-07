package com.example.shoot

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoot.databinding.WelcomeFragmentBinding
import kotlinx.android.synthetic.main.welcome_fragment.view.*

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.welcome_fragment, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.welcome_fragment, container, false)
        view.buttonLetsGo.setOnClickListener{
            //Toast.makeText(this.context, "OK WHAT HAPPEN", Toast.LENGTH_LONG).show()
            view: View ->
            view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_gameInfoFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}