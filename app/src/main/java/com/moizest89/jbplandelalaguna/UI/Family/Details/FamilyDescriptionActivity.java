package com.moizest89.jbplandelalaguna.UI.Family.Details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.Family;
import com.moizest89.jbplandelalaguna.Data.models.FamilyImage;
import com.moizest89.jbplandelalaguna.Data.models.TipImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.Tips.Details.TipsListDetailsActivity;
import com.moizest89.jbplandelalaguna.UI.Tips.Details.TipsListDetailsImagesAdapter;
import com.moizest89.jbplandelalaguna.UI.Tips.Gallery.TipGalleryImagesActivity;
import com.moizest89.jbplandelalaguna.Util.Fonts;
import com.moizest89.jbplandelalaguna.Util.MarginDecoration;
import com.moizest89.jbplandelalaguna.Util.OnItemClickListener;
import com.moizest89.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FamilyDescriptionActivity extends AppCompatActivity implements IFamilyDescriptionView{

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





    private FamilyDescriptionPresenter mPresenter;
    private String mData = "";
    private ProgressDialog mSignUpDialog;

    private final static String TAG = FamilyDescriptionActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_description);

        ButterKnife.bind(this);

        setToolbar();
        setDialog();
        this.TVTDescription.setTypeface(new Fonts().RobotoCondensed_Regular(this));
        this.TVLabel.setTypeface(new Fonts().RobotoCondensed_Regular(this));

        Intent intent = getIntent();
        if(intent != null){
            this.mData = intent.getStringExtra(Util.INTENT_DATA_SEND);
            this.mPresenter = new FamilyDescriptionPresenter(this);
            this.mPresenter.getData(this.mData);
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


    @Override
    public void showData() {

    }

    @Override
    public void showLoader() {
        if (mSignUpDialog != null) {
            mSignUpDialog.show();
        }
    }

    @Override
    public void hideLoader() {
        if(mSignUpDialog.isShowing()){
            mSignUpDialog.dismiss();
        }
    }

    @Override
    public void setMessage(String mMessage) {
        Toast.makeText(FamilyDescriptionActivity.this, mMessage, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setDialog(){
        if (mSignUpDialog == null) {
            mSignUpDialog = new ProgressDialog(this);
            mSignUpDialog.setMessage(this.getString(R.string.global_wait_load));
            mSignUpDialog.setCancelable(false);
        }
    }


    @Override
    public void setData(Family family) {

        if(family != null) {
            this.TVTDescription.setText(Html.fromHtml(family.getDescription()));
            Log.e(TAG, "family.getName(): " + family.getName());

            this.collapsing_toolbar.setTitle(family.getName());

            List<FamilyImage> images = family.getFamilyImages();

            setImages(images);
            if(images.size() > 0) {
                //Banner
                FamilyImage first_image = images.get(0);
                //ImageDetails
                Glide.with(this)
                        .load(first_image.getUrl())
                        .placeholder(R.drawable.holder_rectangular) // can also be a drawable
                        .error(R.drawable.holder_rectangular) // will be displayed if the image cannot be loaded
                        .centerCrop()
                        .into(this.ImageDetails);



            }

        }
    }

    private void setImages(List<FamilyImage> data){
        this.RVList.addItemDecoration(new MarginDecoration(5, this));
        this.RVList.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(this,3);
        this.RVList.setLayoutManager(manager);

        final List<String> mImages = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            FamilyImage familyImage = data.get(i);
            mImages.add(familyImage.getUrl());
        }

        FamilyDescriptionImagesAdapter adapter = new FamilyDescriptionImagesAdapter(this, data);
        this.RVList.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Integer position) {

                Intent intent = new Intent(FamilyDescriptionActivity.this, TipGalleryImagesActivity.class);
                intent.putStringArrayListExtra(Util.INTENT_DATA_SEND,(ArrayList<String>) mImages);
                intent.putExtra(Util.INTENT_DATA_CARROUSEL_POSITION, position);
                startActivity(intent);
            }
        };

        adapter.setOnItemClickListener(onItemClickListener);
    }
}
