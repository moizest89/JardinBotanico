package com.moizest89.jbplandelalaguna.UI.Tips.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.moizest89.jbplandelalaguna.Data.models.TipImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipGalleryImagesActivity extends AppCompatActivity {

    @Bind(R.id.VPimages)
    ViewPager VPimages;


    private List<String> data = new ArrayList<>();
    private Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_gallery_images);

        ButterKnife.bind(this);


        Intent intent = getIntent();
        if(intent != null){
            this.data = intent.getStringArrayListExtra(Util.INTENT_DATA_SEND);
            this.position = intent.getIntExtra(Util.INTENT_DATA_CARROUSEL_POSITION, 0);

            TipGalleryImagesAdaptaer adaptaer = new TipGalleryImagesAdaptaer(this, this.data);

            this.VPimages.setAdapter(adaptaer);
            this.VPimages.setCurrentItem(this.position);
            adaptaer.notifyDataSetChanged();


        }

    }

}
