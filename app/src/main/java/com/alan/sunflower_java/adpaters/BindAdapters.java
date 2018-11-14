package com.alan.sunflower_java.adpaters;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alan.sunflower_java.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * @author by bansen
 * date 2018/10/31.
 */
public class BindAdapters {

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (imageUrl!=null&&!imageUrl.isEmpty()) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);

        }
    }

    @BindingAdapter("isGone")
    public static void bindIsGone(FloatingActionButton view, Boolean isGone) {
        if (isGone == null || isGone) {
            view.hide();
        } else {
            view.show();
        }
    }

    @BindingAdapter("isGone")
    public static void bindIsGone(View view, Boolean isGone) {
        if (isGone == null || isGone) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("wateringText")
    public static void bindWateringText(TextView textView, int wateringInterval) {
        Resources resources = textView.getContext().getResources();
        String tipsString = resources.getString(R.string.watering_needs_prefix);
        String quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
                wateringInterval, wateringInterval);
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(tipsString+" "+quantityString);
        StyleSpan bold=new StyleSpan(Typeface.BOLD);
        StyleSpan italic=new StyleSpan(Typeface.ITALIC);
        stringBuilder.setSpan(bold,0,tipsString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(italic,tipsString.length()+1,quantityString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(stringBuilder);

    }
}
