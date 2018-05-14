package io.github.gianpamx.android.architecture.gallery

import android.content.Context
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import com.bumptech.glide.Glide

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
            ViewHolder(with(SquareImageView(context)) {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, 0)
                scaleType = ImageView.ScaleType.CENTER_CROP
                this
            })

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder(private val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(image: String) {
            Glide.with(context).load(image).into(imageView)
        }
    }
}
