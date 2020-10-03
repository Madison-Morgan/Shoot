package com.example.shoot

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoot.databinding.GameInfoFragmentBinding

class GameInfoFragment : Fragment() {

    companion object {
        fun newInstance() = GameInfoFragment()
    }

    private lateinit var viewModel: GameInfoViewModel
    private lateinit var binding: GameInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.game_info_fragment, container, false)
        binding.button2.setOnClickListener{
            view: View ->
            view.findNavController().navigate(R.id.action_gameInfoFragment_to_gameFragment)
        }
        return inflater.inflate(R.layout.game_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}