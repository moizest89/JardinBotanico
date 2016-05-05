package com.moizest89.jbplandelalaguna.UI.Tips.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.moizest89.jbplandelalaguna.Data.models.Category;
import com.moizest89.jbplandelalaguna.Data.models.Tip;
import com.moizest89.jbplandelalaguna.Data.models.Tips;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.Tips.Main.TipsAdapter;
import com.moizest89.jbplandelalaguna.UI.Tips.Main.TipsPresenter;
import com.moizest89.jbplandelalaguna.Util.Fonts;
import com.moizest89.jbplandelalaguna.Util.MarginDecoration;
import com.moizest89.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipsListActivity extends AppCompatActivity implements  ITipsListView{

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.SWRefresh)
    SwipeRefreshLayout SWRefresh;
    @Bind(R.id.AVLoader)
    AVLoadingIndicatorView AVLoader;
    @Bind(R.id.RVList)
    RecyclerView RVList;

    @Bind(R.id.TVSetMessage)
    TextView TVSetMessage;

    private Category category;
    private TipsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_list);
        ButterKnife.bind(this);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent != null){
            this.category = intent.getParcelableExtra(Util.INTENT_DATA_SEND);
        }


        setToolbar();

        this.RVList.addItemDecoration(new MarginDecoration(1, this));
        this.RVList.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        this.RVList.setLayoutManager(manager);

        this.TVSetMessage.setTypeface(new Fonts().RobotoCondensed_Regular(this));

        this.mPresenter = new TipsListPresenter(this);
        this.mPresenter.getData(this.category.getId());

        this.SWRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TVSetMessage.setAlpha(0);
                showLoading();
            }
        });

    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.tips_title));
        getSupportActionBar().setSubtitle(this.category.getName());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showData() {
        this.SWRefresh.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void hideData() {
        this.SWRefresh.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void setData(List<Tip> tips) {

        TipsListAdapter adapter = new TipsListAdapter(this, tips);
        this.RVList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if(this.SWRefresh.isRefreshing()){
            this.SWRefresh.setRefreshing(false);
        }
    }


    @Override
    public void showLoading() {
        this.AVLoader.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void hideLoading() {
        this.AVLoader.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void setMessageDataError() {
        String mMessage = this.getResources().getString(R.string.data_error);
        this.TVSetMessage.setText("");
        this.TVSetMessage.setText(mMessage);
        this.TVSetMessage.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void setMessageDataEmpty() {
        String mMessage = this.getResources().getString(R.string.data_empty);
        this.TVSetMessage.setText("");
        this.TVSetMessage.setText(mMessage);
        this.TVSetMessage.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

}
