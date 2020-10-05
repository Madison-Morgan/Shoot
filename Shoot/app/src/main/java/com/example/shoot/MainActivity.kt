

package com.example.shoot

import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoot.classifier.ClassifierMove
import com.example.shoot.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private var classifier =  ClassifierMove(this)
    //private var photoFilePath=""
    //private var predictedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
       // NavigationUI.setupActionBarWithNavController(this,navController)
        //predictedTextView = binding.textView2
        //confirmPermissions()
        //classifier.initialize()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }


}