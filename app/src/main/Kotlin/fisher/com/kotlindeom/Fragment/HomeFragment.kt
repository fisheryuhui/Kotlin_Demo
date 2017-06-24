package fisher.com.kotlindeom.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Activity.ComicActivity
import fisher.com.kotlindeom.Adapter.CoverAdapter
import fisher.com.kotlindeom.App
import fisher.com.kotlindeom.Model.Cover
import fisher.com.kotlindeom.netWrok.CoverSource
import fisher.com.kotlindeom.netWrok.ImageSource
import fisher.com.kotlindeom.netWrok.PictureStorySource
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fisher on 2017/6/2.
 */
class HomeFragment : Fragment() {
    companion object {
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }

    var mData = ArrayList<Cover>()
    lateinit var converList: RecyclerView
    lateinit var homeRefresh: SwipeRefreshLayout
    lateinit var adapter: CoverAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        homeRefresh = view.findViewById(R.id.homeRefresh) as SwipeRefreshLayout
        converList = view.findViewById(R.id.homeList) as RecyclerView
        converList.layoutManager = GridLayoutManager(context, 2)
        adapter = CoverAdapter {
            _: View, position: Int ->
            jump2Comic(position)
        }
        converList.adapter = adapter
        homeRefresh.setOnRefreshListener {
            load()
        }
        homeRefresh.post { homeRefresh.isRefreshing = true }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    private fun jump2Comic(position: Int) {
        val intent = Intent(context, ComicActivity::class.java)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].link)
        startActivity(intent)
    }

    private fun load() {
        async {
            val data = CoverSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                homeRefresh.isRefreshing = false
            }
        }
    }
}