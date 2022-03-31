package com.tekun.quizzapp.ui.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.target.ViewTarget
import com.tekun.quizzapp.R


fun ImageView.loadByInternet(resource: String): ViewTarget<ImageView, Drawable> {
    val drawableTransitionOptions: DrawableTransitionOptions =
        if (this.drawable == null) withCrossFade(2000) else withCrossFade(500).dontTransition()

    return Glide.with(this)
        .load(resource)
        .placeholder(R.drawable.clock)
        .error(R.drawable.hzd)
        .fallback(R.drawable.hzd)
        .centerCrop()
        .transition(drawableTransitionOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}





