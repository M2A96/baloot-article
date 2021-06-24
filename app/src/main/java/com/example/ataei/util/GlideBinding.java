package com.example.ataei.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ataei.R;

public class GlideBinding {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();

        RequestOptions options = new RequestOptions().
                placeholder(R.drawable.ic_business_donate_v1)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(imageView);
    }
}
