package com.moizest89.jbplandelalaguna.UI.Family.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.Family;
import com.moizest89.jbplandelalaguna.Data.models.FamilyImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Fonts;
import com.moizest89.jbplandelalaguna.Util.Util;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FamilyDescriptionActivity extends AppCompatActivity implements IFamilyDescriptionView{

    @Bind(R.id.TVTDescription)
    TextView TVTDescription;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ImageDetails)
    ImageView ImageDetails;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;



    private FamilyDescriptionPresenter mPresenter;
    private String mData = "";

    private final static String TAG = FamilyDescriptionActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_description);

        ButterKnife.bind(this);

        setToolbar();

        this.TVTDescription.setTypeface(new Fonts().RobotoCondensed_Regular(this));

        Intent intent = getIntent();
        if(intent != null){
            this.mData = intent.getStringExtra(Util.INTENT_DATA_SEND);
            this.mPresenter = new FamilyDescriptionPresenter(this);
            this.mPresenter.getData(this.mData);
        }

    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hiderLoader() {

    }

    @Override
    public void setData(Family family) {

        if(family != null) {
            this.TVTDescription.setText(Html.fromHtml(family.getDescription()));
            Log.e(TAG, "family.getName(): " + family.getName());

            this.collapsing_toolbar.setTitle(family.getName());

            List<FamilyImage> images = family.getFamilyImages();

            if(images.size() > 0) {
                //Banner
                FamilyImage first_image = images.get(0);
                //ImageDetails
                Glide.with(this)
                        .load(first_image.getUrl())
                        .placeholder(R.drawable.ic_launcher) // can also be a drawable
                        .error(R.drawable.ic_launcher) // will be displayed if the image cannot be loaded
                        .centerCrop()
                        .into(this.ImageDetails);



            }

        }
    }
}
