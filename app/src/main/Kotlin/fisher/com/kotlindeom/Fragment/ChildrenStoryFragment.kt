package fisher.com.kotlindeom.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Activity.PictureStoryDetailActivity
import fisher.com.kotlindeom.Adapter.PictureStoryAdapter
import fisher.com.kotlindeom.Model.PictureStory
import fisher.com.kotlindeom.netWrok.PictureStorySource
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by fisher on 2017/6/9.
 */
class ChildrenStoryFragment : Fragment() {
    companion object {
        val AIM_URL = "http://www.28lu.com/lianhuanhua/List/List_1052.html"
    }

    var mData = ArrayList<PictureStory>()
    lateinit var adapter: PictureStoryAdapter
    lateinit var pictureList: RecyclerView
    lateinit var pictureRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_story, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view!!)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    private fun initView(view: View) {
        pictureList = view.find(R.id.pictureList)
        pictureRefresh = view.find(R.id.pictureRefresh)
        pictureList.layoutManager = GridLayoutManager(context, 2)
        adapter = PictureStoryAdapter {
            _: View, i: Int ->
            jump2PictureDetail(i)
        }
        pictureList.adapter = adapter
        pictureRefresh.setOnRefreshListener { load() }
        pictureRefresh.post { pictureRefresh.isRefreshing = true }
    }

    private fun jump2PictureDetail(position: Int) {
        val intent = Intent(context, PictureStoryDetailActivity::class.java)
        intent.putExtra(PictureStoryDetailActivity.PICTURE_DETAIL_URL, mData[position].link)
        startActivity(intent)

    }

    private fun load() {
        async {
            val data = PictureStorySource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                pictureRefresh.isRefreshing = false
            }
        }
    }

}