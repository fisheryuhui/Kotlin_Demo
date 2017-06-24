package fisher.com.kotlindeom.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Activity.TravelDetailActivity
import fisher.com.kotlindeom.Adapter.TravelStoryAdapter
import fisher.com.kotlindeom.Model.TravelStory
import fisher.com.kotlindeom.netWrok.TravelStorySource
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by fisher on 2017/6/7.
 */
class TravelStoryFragment : Fragment() {
    companion object {
        val AIM_URL = "http://n.quanjing.com/article/tw"
    }

    var mData = ArrayList<TravelStory>()
    lateinit var adapter: TravelStoryAdapter
    lateinit var newsList: RecyclerView
    lateinit var newsRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_travel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    private fun initView(view: View) {
        newsList = view.find(R.id.newsList)
        newsRefresh = view.find(R.id.newsRefresh)
        newsList.layoutManager = LinearLayoutManager(context)
        adapter = TravelStoryAdapter { _: View, i: Int -> jump2TravelDetail(i) }
        newsList.adapter = adapter
        newsRefresh.setOnRefreshListener { load() }

        newsRefresh.post { newsRefresh.isRefreshing = true }
    }

    private fun jump2TravelDetail(position: Int) {
        val it = Intent(activity, TravelDetailActivity::class.java)
        it.putExtra(TravelDetailActivity.TRAVEL_DETAIL_URL, mData[position].link)
        startActivity(it)
    }

    private fun load() {
        async {
            var data = TravelStorySource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                newsRefresh.isRefreshing = false
            }
        }
    }
}