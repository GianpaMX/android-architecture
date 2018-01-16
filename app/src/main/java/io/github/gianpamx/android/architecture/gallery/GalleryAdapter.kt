package io.github.gianpamx.android.architecture.gallery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.gianpamx.androidarchitecture.R

class GalleryAdapter(val context: Context) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private val images = ArrayList<String>()

    fun replaceImages(images: List<String>) {
        this.images.clear()
        this.images.addAll(images)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.gallery_item, parent, false) as ImageView)
    }

    inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(image: String) {
            Glide.with(context).load(image).into(imageView)
        }
    }
}
