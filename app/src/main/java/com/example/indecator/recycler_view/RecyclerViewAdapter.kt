package com.example.indecator.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indecator.databinding.ItemRecyclerBinding

import com.example.viewfrontback.recycler_view.Model

class RecyclerViewAdapter : ListAdapter<Model, RecyclerViewAdapter.CustomViewHolder>(BaseDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CustomViewHolder(private val binding:ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){


        fun onBind(item: Model){

            binding.apply{

                Glide.with(binding.root.context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(binding.imageIv)
            }

            binding.textTv.text = item.title

        }
    }


    class BaseDiffUtilCallBack<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

}

