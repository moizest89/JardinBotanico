package com.jardinbotanico.jbplandelalaguna.UI.Tips.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jardinbotanico.jbplandelalaguna.Data.models.Tip;
import com.jardinbotanico.jbplandelalaguna.Data.models.TipImage;
import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.Tips.Gallery.TipGalleryImagesActivity;
import com.jardinbotanico.jbplandelalaguna.Util.Fonts;
import com.jardinbotanico.jbplandelalaguna.Util.MarginDecoration;
import com.jardinbotanico.jbplandelalaguna.Util.OnItemClickListener;
import com.jardinbotanico.jbplandelalaguna.Util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipsListDetailsActivity extends AppCompatActivity {

    @Bind(R.id.TVTDescription)
    TextView TVTDescription;
    @Bind(R.id.TVLabel)
    TextView TVLabel;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Bind(R.id.ImageDetails)
    ImageView ImageDetails;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @Bind(R.id.RVList)
    RecyclerView RVList;


    private Tip tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_list_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);

        setToolbar();


        //Coloco las fuentes al texto
        this.TVTDescription.setTypeface(new Fonts().RobotoCondensed_Regular(this));
        this.TVLabel.setTypeface(new Fonts().RobotoCondensed_Regular(this));


        //Obtengo la informacion de la vista anterior
        Intent intent = getIntent();
        if(intent != null){
            this.tip =  intent.getExtras().getParcelable(Util.INTENT_DATA_SEND);

            TVTDescription.setText(Html.fromHtml(this.tip.getDescription()));
            this.collapsing_toolbar.setTitle(this.tip.getName());

            List<TipImage> tipImages = this.tip.getTipImages();

            if(tipImages.size() > 0) {

                //Banner
                TipImage tipImage = tipImages.get(0);
                //ImageDetails
                Glide.with(this)
                        .load(Util.modifyDropboxUrl(tipImage.getUrl()))
                        .placeholder(R.drawable.holder_rectangular)
                        .error(R.drawable.holder_rectangular)
                        .centerCrop()
                        .into(this.ImageDetails);

                setImagesGrid(tipImages);

            }

        }else{

        }

    }

    private void setToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    //Coloco las imagenes de la galeria
    private void setImagesGrid(final List<TipImage> data){
        this.RVList.addItemDecoration(new MarginDecoration(5, this));
        this.RVList.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(this,3);
        this.RVList.setLayoutManager(manager);

        TipsListDetailsImagesAdapter adapter = new TipsListDetailsImagesAdapter(this, data);
        this.RVList.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        final List<String> mImages = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            TipImage tipImage = data.get(i);
            mImages.add(tipImage.getUrl());
        }


        //Coloco el evento cuando hago touch en la imagen que me lleva al detalle
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Integer position) {
                Intent intent = new Intent(TipsListDetailsActivity.this, TipGalleryImagesActivity.class);

                intent.putStringArrayListExtra(Util.INTENT_DATA_SEND,(ArrayList<String>) mImages);
                intent.putExtra(Util.INTENT_DATA_CARROUSEL_POSITION, position);
                startActivity(intent);
            }
        };

        adapter.setOnItemClickListener(onItemClickListener);

    }

    private void goToDetails(){

    }

}
