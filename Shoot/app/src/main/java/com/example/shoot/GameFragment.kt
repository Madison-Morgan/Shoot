package com.example.shoot

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shoot.classifier.PhotoActivity
import com.example.shoot.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.game_fragment.view.*
import java.io.File


private const val REQUEST_PERMISSIONS = 1
private const val REQUEST_TAKE_PICTURE = 2


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding
    private var predictedTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.game_fragment, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.game_fragment, container, false)
        predictedTextView = binding.textViewResult
        view.buttonTakePicture.setOnClickListener {
            val intent = Intent(activity, PhotoActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}