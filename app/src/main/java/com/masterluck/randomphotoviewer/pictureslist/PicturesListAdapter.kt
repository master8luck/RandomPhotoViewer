package com.masterluck.randomphotoviewer.pictureslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masterluck.randomphotoviewer.PhotoDao
import com.masterluck.randomphotoviewer.databinding.ItemPicturesListBinding

class PicturesListAdapter(
    private val context: Context,
    private val onPhotoClicked: (String) -> Unit,
) : RecyclerView.Adapter<PicturesListAdapter.PicturesListViewHolder>() {

    private var photos = listOf<PhotoDao>()

    fun setupPhotos(newPhotos: List<PhotoDao>) {
        photos = newPhotos
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesListViewHolder {
        return PicturesListViewHolder(
            ItemPicturesListBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PicturesListViewHolder, position: Int) {
        holder.bind(photos[position], onPhotoClicked)
    }

    override fun getItemCount(): Int {
        return photos.size
    }


    class PicturesListViewHolder(itemPicturesListBinding: ItemPicturesListBinding) :
        RecyclerView.ViewHolder(itemPicturesListBinding.root) {

        private val binding = itemPicturesListBinding

        fun bind(photo: PhotoDao, onPhotoClicked: (String) -> Unit) {
            binding.root.setOnClickListener{ onPhotoClicked(photo.rawUrl) }
            Glide.with(binding.root).load(photo.thumbUrl).into(binding.ivPicture)
            binding.tvDescription.text = photo.description
        }

    }
}