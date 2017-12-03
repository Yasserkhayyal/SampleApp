package com.example.android.sampleapp.views;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sampleapp.R;
import com.example.android.sampleapp.adapters.MenuItemsAdapter;
import com.example.android.sampleapp.interfaces.Callback;
import com.example.android.sampleapp.models.ResponseModel;
import com.example.android.sampleapp.presenters.PresenterImp;
import com.example.android.sampleapp.utils.RetrofitInitializer;

import java.io.IOException;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements Callback {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.items_rv)
    RecyclerView recyclerView;

    @BindView(R.id.loading_pb)
    ProgressBar mLoadingIndicator;

    @BindView(R.id.error_tv)
    TextView errorTv;

    PresenterImp presenterImp;
    private final int DATA_LOADER = 10;
    MenuItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        RetrofitInitializer retrofitInitializer = new RetrofitInitializer(this);
        presenterImp = new PresenterImp(this, retrofitInitializer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(itemDecoration);
        mAdapter = new MenuItemsAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterImp.getMenuItems();
        showLoadingIndicator(true);
    }

    @Override
    public void onSuccess(ResponseModel response) {
        showLoadingIndicator(false);
        showErrorMsg(false);
        mAdapter.setItems(response.getItems());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onError(ResponseBody error) {
        showLoadingIndicator(false);
        try {
            errorTv.setText(error.string());
        } catch (IOException e) {
            e.printStackTrace();
            errorTv.setText(R.string.error_msg);
        }
        showErrorMsg(true);
    }

    @Override
    public void onFailure(Throwable throwable) {
        showLoadingIndicator(false);
        if(throwable instanceof UnknownHostException){
            errorTv.setText(R.string.no_internet_msg);
        }else {
            errorTv.setText(throwable.getMessage());
        }
        showErrorMsg(true);
    }

    private void showLoadingIndicator(boolean show){
        mLoadingIndicator.setVisibility(show? View.VISIBLE:View.GONE);
    }

    private void showErrorMsg(boolean showError){
        recyclerView.setVisibility(showError? View.GONE:View.VISIBLE);
        errorTv.setVisibility(showError? View.VISIBLE:View.GONE);
    }
}
