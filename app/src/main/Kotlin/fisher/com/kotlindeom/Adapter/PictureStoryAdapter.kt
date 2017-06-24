package fisher.com.kotlindeom.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Model.Cover
import fisher.com.kotlindeom.Model.PictureStory
import kotlinx.android.synthetic.main.item_cover.view.*

/**
 * Created by fisher on 2017/6/9.
 */
class PictureStoryAdapter(var data : List<PictureStory> = ArrayList<PictureStory>(), var itemClick:(View,Int) -> Unit) :RecyclerView.Adapter<PictureStoryAdapter.CoverViewHolder>(){

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cover, parent, false)
        return CoverViewHolder(itemView)
    }

    fun refreshData(newData: List<PictureStory>) {
        data = newData
        notifyDataSetChanged()
    }

    private fun bindView(itemView: View, position: Int) {
        val cover = data[position]
        itemView.tv_cover.text = cover.title
        Picasso.with(itemView.context).load(cover.coverUrl).into(itemView.iv_cover)
        itemView.coverContainer.setOnClickListener {
            itemClick(itemView, position)
        }

    }

    class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}