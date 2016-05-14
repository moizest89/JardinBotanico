package com.moizest89.jbplandelalaguna.UI.Family.Details;

import android.app.ProgressDialog;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.Family;
import com.moizest89.jbplandelalaguna.Data.models.FamilyImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Fonts;
import com.moizest89.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

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
}
