package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.s_activity_main_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.istockphoto.com/vector/abstract-white-and-gray-modern-background-vector-illustration-gm1157674417-315954054"));
        slideModels.add(new SlideModel("https://www.istockphoto.com/photo/brown-wood-texture-dark-wooden-abstract-background-gm1137996207-303653020"));
        imageSlider.setImageList(slideModels,true);
    }
}