package com.example.indecator

import android.R.attr.button
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.indecator.databinding.ActivityMainBinding
import com.example.indecator.pager_view.CustomPagerAdapter
import com.example.indecator.recycler_view.RecyclerViewAdapter
import com.example.viewfrontback.recycler_view.MockModel
import com.example.viewfrontback.recycler_view.Model
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class MainActivity : AppCompatActivity() {

    private val viewModel: LocalViewHolder = LocalViewHolder()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setRecyclerView()

        addDot(viewModel.sliderList, binding.dotIndicator.indicatorContainer)
        drawIndicatorOfScrollPositionDynamic(binding.dotIndicator.indicatorContainer,0)

    }
    // am adding the dot indicator at run time

    //this done by add bots base on the list size and then draw line where current user scroll

   /*
       draw new dot in the indicator (draw all the dots fro the first time)
   */
    private fun addDot(list: List<Model>, linearLayout: LinearLayout) {
       // here am check how much the item and add the dot at run time
        for (item in list) {
            val imageView = ImageView(this).apply {
                this.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.circle_selected_slider,
                        context.theme
                    )
                )

                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                layoutParams.setMargins(0,0,4,0)
                this.layoutParams = layoutParams
            }

           linearLayout.addView(imageView)
        }

    }

    private fun setRecyclerView() {
        binding.recyclerViewItem.let {

            val adapter = RecyclerViewAdapter()

            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            it.layoutManager = layoutManager
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(it)
            it.adapter = adapter
            adapter.submitList(MockModel.DataList)
            observeRecyclerViewScroll(it, layoutManager)
        }
    }


    private fun observeRecyclerViewScroll(
        it: RecyclerView,
        layoutManager: LinearLayoutManager
    ) {
        it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (layoutManager.findFirstCompletelyVisibleItemPosition() >= 0) {

                    Log.d("TAG", "onScrolled: observeRecyclerViewScroll inside ${layoutManager.findFirstCompletelyVisibleItemPosition()}")

                    drawIndicatorOfScrollPositionDynamic(binding.dotIndicator.indicatorContainer, layoutManager.findFirstCompletelyVisibleItemPosition())
                }

            }
        })
    }

    // draw the line where the current position of the user in the recyclerview
    private fun drawIndicatorOfScrollPositionDynamic(linearLayout: LinearLayout,position:Int){

        initIndicatorDotDrawing(linearLayout)


        val view =linearLayout.getChildAt(position)

        (view as ImageView).setImageDrawable(ResourcesCompat.getDrawable(
            resources,
            R.drawable.shp_res_background_milky,
            this.theme
        ))

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(2,0,4,0)

        view.layoutParams = layoutParams

    }

    private fun initIndicatorDotDrawing(
        linearLayout: LinearLayout
    ) {

        val size = linearLayout.childCount

        for (i in 0 until size) {

            val view = linearLayout.getChildAt(i)


            (view as ImageView).setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.circle_selected_slider,
                    this.theme
                )
            )

            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(0,0,4,0)

            view.layoutParams = layoutParams

        }
    }


    private fun setMain(rv: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.HORIZONTAL)
        ResourcesCompat.getDrawable(resources, R.drawable.zig_zag, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        rv.addItemDecoration(dividerItemDecoration)
    }

}


class LocalViewHolder {

    val sliderList: List<Model> = MockModel.DataList // it 8 item for now so the indicator generic
    var position: Int = 0

}




//    private fun drawIndicatorOfScrollPositionFixSize(position: Int) {
//
//        Log.d("TAG", "onScrolled:selectedSlider $position")
//
//        binding.selectedSlider.imvFirst.setImageDrawable(
//            ResourcesCompat.getDrawable(
//                resources,
//                R.drawable.circle_selected_slider,
//                this.theme
//            )
//        )
//        binding.selectedSlider.imvSecond.setImageDrawable(
//            ResourcesCompat.getDrawable(
//                resources,
//                R.drawable.circle_selected_slider,
//                this.theme
//            )
//        )
//        binding.selectedSlider.imvThird.setImageDrawable(
//            ResourcesCompat.getDrawable(
//                resources,
//                R.drawable.circle_selected_slider,
//                this.theme
//            )
//        )
//
//        when (position) {
//            0 -> {
//
//                binding.currentString.text = "position $position"
//
//                binding.selectedSlider.imvFirst.setImageDrawable(
//                    ResourcesCompat.getDrawable(
//                        resources,
//                        R.drawable.shp_res_background_milky,
//                        this.theme
//                    )
//                )
//            }
//            viewModel.sliderList.size - 1 -> {
//
//                binding.currentString.text = "position $position"
//
//
//                binding.selectedSlider.imvThird.setImageDrawable(
//                    ResourcesCompat.getDrawable(
//                        resources,
//                        R.drawable.shp_res_background_milky,
//                        this.theme
//                    )
//                )
//            }
//            else -> {
//
//                binding.currentString.text = "werid position $position"
//
//                binding.selectedSlider.imvSecond.setImageDrawable(
//                    ResourcesCompat.getDrawable(
//                        resources,
//                        R.drawable.shp_res_background_milky,
//                        this.theme
//                    )
//                )
//            }
//        }
//    }
