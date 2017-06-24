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
import fisher.com.kotlindeom.Adapter.CoverAdapter
import fisher.com.kotlindeom.Adapter.PictureStoryAdapter
import fisher.com.kotlindeom.Model.Cover
import fisher.com.kotlindeom.Model.PictureStory
import fisher.com.kotlindeom.netWrok.BookSource
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by fisher on 2017/6/7.
 */
class BookFragment : Fragment() {
    companion object {
        val AIM_URL = "http://www.28lu.com/lianhuanhua/List/List_1050.html"
    }

    var mData = ArrayList<PictureStory>()
    lateinit var bookList: RecyclerView
    lateinit var bookRefresh: SwipeRefreshLayout
    lateinit var adapter: PictureStoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        bookList = view.find(R.id.bookList)
        bookRefresh = view.find(R.id.bookRefresh)
        bookList.layoutManager = GridLayoutManager(context, 2)
        adapter = PictureStoryAdapter(mData, {
            _: View, i: Int ->
            jump2Detail(i)
        })
        bookList.adapter = adapter
        bookRefresh.setOnRefreshListener { load() }
        bookRefresh.post { bookRefresh.isRefreshing = true }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    private fun jump2Detail(position: Int) {
        val intent = Intent(activity, PictureStoryDetailActivity::class.java)
        intent.putExtra(PictureStoryDetailActivity.PICTURE_DETAIL_URL, mData[position].link)
        startActivity(intent)
    }

    private fun load() {
        async {
            val data = BookSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                bookRefresh.isRefreshing = false
            }
        }
    }
}