package id.ac.digind.gasskos.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.digind.gasskos.R;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private ViewGroup vFilterRating, vFilterGender, vFilterHarga, vFilterLokasi;
    private ImageView imgFilterRating, imgFilterGender, imgFilterHarga, imgFilterLokasi;
    private TextView tvFilterRating, tvFilterGender, tvFilterHarga, tvFilterLokasi;
    private ViewGroup vRating, vGender, vHarga;
    private ImageView imgStar1, imgStar2, imgStar3, imgStar4, imgStar5;

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
    }

    private void addListener() {
        vFilterRating.setOnClickListener(this);
        vFilterGender.setOnClickListener(this);
        vFilterHarga.setOnClickListener(this);
        vFilterLokasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_filter_rating:
                setImgTint(R.color.colorPrimary, imgFilterRating);
                setTextViewColor(R.color.colorPrimary, tvFilterRating);
                setImgTint(android.R.color.black, imgFilterGender, imgFilterHarga, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterGender, tvFilterHarga, tvFilterLokasi);
                break;
            case R.id.v_filter_gender:
                setImgTint(R.color.colorPrimary, imgFilterGender);
                setTextViewColor(R.color.colorPrimary, tvFilterGender);
                setImgTint(android.R.color.black, imgFilterRating, imgFilterHarga, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterHarga, tvFilterLokasi);
                break;
            case R.id.v_filter_harga:
                setImgTint(R.color.colorPrimary, imgFilterHarga);
                setTextViewColor(R.color.colorPrimary, tvFilterHarga);
                setImgTint(android.R.color.black, imgFilterRating, imgFilterGender, imgFilterLokasi);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterGender, tvFilterLokasi);
                break;
            case R.id.v_filter_lokasi:
                setImgTint(R.color.colorPrimary, imgFilterLokasi);
                setTextViewColor(R.color.colorPrimary, tvFilterLokasi);
                setImgTint(android.R.color.black, imgFilterRating, imgFilterGender, imgFilterHarga);
                setTextViewColor(android.R.color.tertiary_text_light, tvFilterRating, tvFilterGender, tvFilterHarga);
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
}