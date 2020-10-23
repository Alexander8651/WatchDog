package com.andromoticaia.watchingdog

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// donwlod the images from url to put in image view
@BindingAdapter("imageURL")
fun bindImage(imgView:ImageView, imgUrl:String){

    imgUrl.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}