package com.example.indecator.pager_view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.indecator.R
import com.example.indecator.databinding.ItemRecyclerBinding
import com.example.viewfrontback.recycler_view.MockModel
import com.example.viewfrontback.recycler_view.Model


class CustomPagerAdapter (private val mContext: Context, /*private val itemList: ArrayList<Model>*/) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    val itemList = arrayListOf(
        Model("Hello To Main Screen", MockModel.image1),
        Model("Have Great Summer", MockModel.image2),
        Model("Getting weird", MockModel.image3),
        Model("Run for you Live", MockModel.image4),
        Model("Run for you Live", MockModel.image4)
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_recycler, container, false)

        var textview: TextView = view.findViewById(R.id.textTv)
        var imageView: ImageView = view.findViewById(R.id.imageIv)

        textview.text = itemList[position].title
        //imageView.setImageDrawable(itemList[position].imageUrl)
        Glide.with(mContext)
            .load(itemList[position].imageUrl)
            .centerCrop()
            .into(imageView)

        Log.d("TAG", "instantiateItem: ${position}")

        container.addView(view, position-2)
        return view
    }
    override fun getCount(): Int {
        return itemList.size
    }
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}