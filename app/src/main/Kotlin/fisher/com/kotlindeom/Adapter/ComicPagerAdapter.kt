package fisher.com.kotlindeom.Adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import fisher.com.kotlindeom.Fragment.ComicFragment
import fisher.com.kotlindeom.Model.Comic

/**
 * Created by fisher on 2017/6/7.
 */
class ComicPagerAdapter(var data: ArrayList<Comic> = ArrayList<Comic>(), fragmentManger: FragmentManager) : FragmentPagerAdapter(fragmentManger) {

    override fun getItem(position: Int): Fragment {
        return newInstance(data[position].comicUrl)
    }

    override fun getCount(): Int {
        return data.size
    }

    fun newInstance(url: String): Fragment {
        return ComicFragment.newInstance(url)
    }

    fun refreshData(newData : ArrayList<Comic>) {
        data = newData
        notifyDataSetChanged()
    }

}