package id.ac.digind.gasskos.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.digind.gasskos.R;
import id.ac.digind.gasskos.adapters.FilterKostAdapter;
import id.ac.digind.gasskos.models.Kost;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private ViewGroup vFilterRating, vFilterGender, vFilterHarga, vFilterLokasi;
    private ImageView imgFilterRating, imgFilterGender, imgFilterHarga, imgFilterLokasi;
    private TextView tvFilterRating, tvFilterGender, tvFilterHarga, tvFilterLokasi;
    private ViewGroup vRating, vGender, vHarga;
    private ImageView imgStar1, imgStar2, imgStar3, imgStar4, imgStar5;
    private TextView tvPria, tvWanita, tvCampuran;
    private NestedScrollView vItem;
    private TextView tvMaps;
    private RecyclerView recyclerView;
    private FilterKostAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        addListener();
    }

    private void initView(View v) {
        vFilterRating = v.findViewById(R.id.v_filter_rating);
        vFilterGender= v.findViewById(R.id.v_filter_gender);
        vFilterHarga = v.findViewById(R.id.v_filter_harga);
        vFilterLokasi = v.findViewById(R.id.v_filter_lokasi);
        imgFilterRating = v.findViewById(R.id.img_filter_rating);
        imgFilterGender= v.findViewById(R.id.img_filter_gender);
        imgFilterHarga = v.findViewById(R.id.img_filter_harga);
        imgFilterLokasi = v.findViewById(R.id.img_filter_lokasi);
        tvFilterRating = v.findViewById(R.id.tv_filter_rating);
        tvFilterGender= v.findViewById(R.id.tv_filter_gender);
        tvFilterHarga = v.findViewById(R.id.tv_filter_harga);
        tvFilterLokasi = v.findViewById(R.id.tv_filter_lokasi);

        vRating = v.findViewById(R.id.v_rating);
        vGender = v.findViewById(R.id.v_gender);
        vHarga = v.findViewById(R.id.v_harga);

        imgStar1 = v.findViewById(R.id.img_star1);
        imgStar2 = v.findViewById(R.id.img_star2);
        imgStar3 = v.findViewById(R.id.img_star3);
        imgStar4 = v.findViewById(R.id.img_star4);
        imgStar5 = v.findViewById(R.id.img_star5);
        
        tvPria = v.findViewById(R.id.tv_pria);
        tvWanita = v.findViewById(R.id.tv_wanita);
        tvCampuran = v.findViewById(R.id.tv_campuran);

        vItem = v.findViewById(R.id.v_item);

        tvMaps = v.findViewById(R.id.tv_maps);

        recyclerView = v.findViewById(R.id.rv_kost);
        adapter = new FilterKostAdapter(Kost.dummyDataFilter(10));
        recyclerView.setAdapter(adapter);
    }

    private void addListener() {
        vFilterRating.setOnClickListener(this);
        vFilterGender.setOnClickListener(this);
        vFilterHarga.setOnClickListener(this);
        vFilterLokasi.setOnClickListener(this);

        imgStar1.setOnClickListener(this);
        imgStar2.setOnClickListener(this);
        imgStar3.setOnClickListener(this);
        imgStar4.setOnClickListener(this);
        imgStar5.setOnClickListener(this);
        
        tvPria.setOnClickListener(this);
        tvWanita.setOnClickListener(this);
        tvCampuran.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_filter_rating:
                setImgTint(R.color.colorPrimary, imgFilterRating);
                setTextViewColor(R.color.colorPrimary, tvFilterRating);
                setImgTint(android.R.color.tertiary_text_light, imgFilterGender, imgFilterHarga, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterGender, tvFilterHarga, tvFilterLokasi);
                setVisibility(View.VISIBLE, vRating);
                setVisibility(View.GONE, vGender, vHarga);
                vItem.setVisibility(View.VISIBLE);
                tvMaps.setVisibility(View.GONE);
                break;
            case R.id.v_filter_gender:
                setImgTint(R.color.colorPrimary, imgFilterGender);
                setTextViewColor(R.color.colorPrimary, tvFilterGender);
                setImgTint(android.R.color.tertiary_text_light, imgFilterRating, imgFilterHarga, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterHarga, tvFilterLokasi);
                setVisibility(View.VISIBLE, vGender);
                setVisibility(View.GONE, vRating, vHarga);
                vItem.setVisibility(View.VISIBLE);
                tvMaps.setVisibility(View.GONE);
                break;
            case R.id.v_filter_harga:
                setImgTint(R.color.colorPrimary, imgFilterHarga);
                setTextViewColor(R.color.colorPrimary, tvFilterHarga);
                setImgTint(android.R.color.tertiary_text_light, imgFilterRating, imgFilterGender, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterGender, tvFilterLokasi);
                setVisibility(View.VISIBLE, vHarga);
                setVisibility(View.GONE, vRating, vGender);
                vItem.setVisibility(View.VISIBLE);
                tvMaps.setVisibility(View.GONE);
                break;
            case R.id.v_filter_lokasi:
                setImgTint(R.color.colorPrimary, imgFilterLokasi);
                setTextViewColor(R.color.colorPrimary, tvFilterLokasi);
                setImgTint(android.R.color.tertiary_text_light, imgFilterRating, imgFilterGender, imgFilterHarga);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterGender, tvFilterHarga);
                setVisibility(View.GONE, vRating, vGender, vHarga);
                vItem.setVisibility(View.GONE);
                tvMaps.setVisibility(View.VISIBLE);
                break;
        }

        switch (view.getId()){
            case R.id.img_star1:
                setStar(1);
                break;
            case R.id.img_star2:
                setStar(2);
                break;
            case R.id.img_star3:
                setStar(3);
                break;
            case R.id.img_star4:
                setStar(4);
                break;
            case R.id.img_star5:
                setStar(5);
                break;
        }

        switch (view.getId()){
            case R.id.tv_pria:
                setTextViewColor(R.color.colorPrimary, tvPria);
                setTextViewColor(android.R.color.tertiary_text_light, tvWanita, tvCampuran);
                tvPria.setClickable(false);
                tvWanita.setClickable(true);
                tvCampuran.setClickable(true);
                break;
            case R.id.tv_wanita:
                setTextViewColor(R.color.colorPrimary, tvWanita);
                setTextViewColor(android.R.color.tertiary_text_light, tvPria, tvCampuran);
                tvPria.setClickable(true);
                tvWanita.setClickable(false);
                tvCampuran.setClickable(true);
                break;
            case R.id.tv_campuran:
                setTextViewColor(R.color.colorPrimary, tvCampuran);
                setTextViewColor(android.R.color.tertiary_text_light, tvPria, tvWanita);
                tvPria.setClickable(true);
                tvWanita.setClickable(true);
                tvCampuran.setClickable(false);
                break;
        }
    }

    private void setImgTint(int color, ImageView... imgs) {
        for (ImageView img : imgs) {
            ImageViewCompat.setImageTintList(img,
                    ColorStateList.valueOf(ContextCompat.getColor(context, color)));
        }
    }

    private void setTextViewColor(int color, TextView... tvs) {
        for (TextView tv : tvs) {
            tv.setTextColor(ContextCompat.getColor(context, color));
        }
    }

    private void setVisibility(int visibility, ViewGroup... vs){
        for (ViewGroup v : vs) {
            v.setVisibility(visibility);
        }
    }

    private void setStar(int count){
        if (count >= 1) {
            imgStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 2) {
            imgStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 3) {
            imgStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 4) {
            imgStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 5) {
            imgStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }
    }
}