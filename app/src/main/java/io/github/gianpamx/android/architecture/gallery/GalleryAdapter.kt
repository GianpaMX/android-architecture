package io.github.gianpamx.android.architecture.gallery

import android.content.Context
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.gianpamx.androidarchitecture.R

class GalleryAdapter(private val context: Context) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private val differ = AsyncListDiffer<String>(this, NewCallback())

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = differ.currentList[position].hashCode().toLong()

    override fun getItemCount(): Int = differ.currentList.size


    fun replaceImages(images: List<String>) {
        differ.submitList(images)
    }

    class NewCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String?, newItem: String?) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String?, newItem: String?) = oldItem == newItem
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(View.inflate(parent.context, R.layout.gallery_item, parent) as ImageView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder(private val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(image: String) {
            Glide.with(context).load(image).into(imageView)
        }
    }
}
