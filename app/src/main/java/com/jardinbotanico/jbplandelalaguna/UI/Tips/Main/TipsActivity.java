package com.jardinbotanico.jbplandelalaguna.UI.Tips.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jardinbotanico.jbplandelalaguna.Data.models.Categories;
import com.jardinbotanico.jbplandelalaguna.Data.models.Category;
import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.Tips.List.TipsListActivity;
import com.jardinbotanico.jbplandelalaguna.Util.MarginDecoration;
import com.jardinbotanico.jbplandelalaguna.Util.OnItemClickListener;
import com.jardinbotanico.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipsActivity extends AppCompatActivity implements ITipsView{

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.SWRefresh)
    SwipeRefreshLayout SWRefresh;
    @Bind(R.id.AVLoader)
    AVLoadingIndicatorView AVLoader;
    @Bind(R.id.RVList)
    RecyclerView RVList;

    @Bind(R.id.TVSetMessage)
    TextView TVSetMessage;


    private TipsPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ButterKnife.bind(this);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar();

        RVList.addItemDecoration(new MarginDecoration(1, this));
        RVList.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        RVList.setLayoutManager(manager);

        this.mPresenter = new TipsPresenter(this);
        this.mPresenter.getData();

    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.tips_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Muestro la informacion
    @Override
    public void showData() {
        this.SWRefresh.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }


    //Oculto la informacion
    @Override
    public void hideData() {
        this.SWRefresh.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }


    //Colocar la informacion en la vista
    @Override
    public void setData(final Categories categories) {
        TipsAdapter adapter = new TipsAdapter(this, categories.getCategories());
        this.RVList.setAdapter(adapter);

        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Integer position) {

                goToTipList(categories.getCategories().get(position));

            }
        };
        adapter.setOnItemClickListener(onItemClickListener);

    }

    //Muestro el loader
    @Override
    public void showLoading() {
        this.AVLoader.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    //Oculto el loader
    @Override
    public void hideLoading() {
        this.AVLoader.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }


    //Me envia a la vista del detalle de la categoria
    @Override
    public void goToTipList(Category category) {

        Intent intent = new Intent(this, TipsListActivity.class);
        intent.putExtra(Util.INTENT_DATA_SEND, category);
        startActivity(intent);

    }


    //Muestro un mensaje de error
    @Override
    public void setMessageDataError() {
        String mMessage = this.getResources().getString(R.string.data_error);
        this.TVSetMessage.setText("");
        this.TVSetMessage.setText(mMessage);
        this.TVSetMessage.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    //Si no existe informacion muestro un mensaje
    @Override
    public void setMessageDataEmpty() {
        String mMessage = this.getResources().getString(R.string.data_empty);
        this.TVSetMessage.setText("");
        this.TVSetMessage.setText(mMessage);
        this.TVSetMessage.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }


}
