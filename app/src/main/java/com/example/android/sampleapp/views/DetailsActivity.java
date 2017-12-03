package com.example.android.sampleapp.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sampleapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Yasser on 12/2/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.item_details_iv)
    ImageView itemImage;

    @BindView(R.id.item_description)
    TextView itemDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        ViewCompat.setTransitionName(appBarLayout,
                getString(R.string.menu_item_transition));
        supportPostponeEnterTransition();

        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat
                .getColor(this,R.color.semi_transparent_white));


        if(getIntent().hasExtra(getString(R.string.image_url))){
            String title = getIntent().getStringExtra(getString(R.string.title));
            String description = getIntent().getStringExtra(getString(R.string.description));

            collapsingToolbarLayout.setTitle(title);
            itemDesc.setText(description);
            String photoUrl = getIntent().getStringExtra(getString(R.string.image_url));

            Picasso.with(this)
                    .load(photoUrl)
                    .fit()
                    .noFade()
                    .into(itemImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
