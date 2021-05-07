package com.example.opencvlearning.demo.image_processing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.opencvlearning.R
import com.github.dhaval2404.imagepicker.ImagePicker

class SmoothingImagesActivity : AppCompatActivity() {
    private lateinit var bt_picker: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smoothing_images);
        bt_picker = findViewById(R.id.bt_smoothing_image_picker)
        bt_picker.setOnClickListener{
            ImagePicker.with(this).compress(1024).start()
        }
    }
}