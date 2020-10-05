package com.example.shoot

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.shoot.classifier.ClassifierMove
import com.example.shoot.databinding.GameFragmentBinding
import java.io.File


private const val REQUEST_PERMISSIONS = 1
private const val REQUEST_TAKE_PICTURE = 2


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding
    private var classifier = this.context?.let { ClassifierMove(it) }
    private var photoFilePath=""
    private var predictedTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.game_fragment, container, false)
        predictedTextView = binding.textViewResult
        binding.buttonTakePicture.setOnClickListener {
            confirmPermissions()
            classifier?.initialize()
        }
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun confirmPermissions() {
        var permissionAlreadyGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if(permissionAlreadyGranted){
            takePhoto()
        }
        else{
            requestPermissions()
        }
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var permissionGranted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        if(requestCode== REQUEST_PERMISSIONS && permissionGranted){
            takePhoto()
        }
        else{
            requestPermissions()
        }

    }

    private fun takePhoto(){
        photoFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/${System.currentTimeMillis()}.jpg"
        val currentPhotoUri = FileProvider.getUriForFile(this, "com.example.shoot.provider", File(photoFilePath))

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        takePictureIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        if (takePictureIntent.resolveActivity(PackageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PICTURE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val file = File(photoFilePath)
        if (requestCode == REQUEST_TAKE_PICTURE && file.exists()) {
            classifyMove(file)
        }
    }


    private fun classifyMove(file: File){
        //get photo and classify here
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)

        if ((bitmap != null) && (classifier.isInitialized)) {
            classifier
                .classifyAsync(bitmap)
                .addOnSuccessListener { resultText -> predictedTextView?.text = resultText }
                .addOnFailureListener { e ->
                    predictedTextView?.text = "ffuuuckkkk"
                    Log.e(MainActivity, "Error classifying drawing.", e)
                }
        }
    }

}