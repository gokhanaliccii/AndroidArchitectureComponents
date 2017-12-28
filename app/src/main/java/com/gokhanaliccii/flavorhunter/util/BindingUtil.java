package com.gokhanaliccii.flavorhunter.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by gokhan on 28/12/17.
 */

public class BindingUtil {

    @BindingAdapter(value = {"url","placeholder"}, requireAll = false)
    public static void loadImageUrl(ImageView view, String url, Drawable placeHolder) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext())
                    .load(url).placeholder(placeHolder)
                    .into(view);
        }
    }
}
