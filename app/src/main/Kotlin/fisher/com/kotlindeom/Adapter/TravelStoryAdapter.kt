package fisher.com.kotlindeom.Adapter

import android.icu.text.UnicodeSetIterator
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Model.TravelStory
import kotlinx.android.synthetic.main.item_cover.view.*
import kotlinx.android.synthetic.main.item_travel.view.*
import org.jetbrains.anko.onClick


/**
 * Created by fisher on 2017/6/7.
 */
class TravelStoryAdapter(var data: List<TravelStory> = ArrayList(),var itemClick:(View,Int)->Unit) : RecyclerView.Adapter<TravelStoryAdapter.NewsContainerAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelStoryAdapter.NewsContainerAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_travel, parent, false)
        return TravelStoryAdapter.NewsContainerAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TravelStoryAdapter.NewsContainerAdapterViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    private fun bindView(itemView: View, position: Int) {
        val travel = data[position]
        itemView.tv_container_title.text = travel.title
        Picasso.with(itemView.context).load(travel.coverUrl).into(itemView.travel_image)
        itemView.setOnClickListener { itemClick(itemView,position) }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun refreshData(newData: List<TravelStory>) {
        data = newData
        notifyDataSetChanged()
    }

    class NewsContainerAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}