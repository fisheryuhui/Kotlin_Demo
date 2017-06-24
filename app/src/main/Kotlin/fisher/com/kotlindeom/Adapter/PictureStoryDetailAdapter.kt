package fisher.com.kotlindeom.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import fisher.com.kotlindeom.Fragment.ComicFragment
import fisher.com.kotlindeom.Model.Comic

/**
 * Created by fisher on 2017/6/9.
 */
class PictureStoryDetailAdapter(var data: ArrayList<Comic> = ArrayList(), fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return newInstance(data[position].comicUrl)
    }

    override fun getCount(): Int {
        return data.size
    }

    fun newInstance(url: String): Fragment {
        return ComicFragment.newInstance(url)
    }

    fun refreshData(newData: ArrayList<Comic>) {
        data = newData
        notifyDataSetChanged()
    }
}