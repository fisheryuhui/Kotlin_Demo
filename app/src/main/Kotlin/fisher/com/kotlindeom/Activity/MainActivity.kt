package fisher.com.kotlindeom.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Adapter.ContentPagerAdapter
import fisher.com.kotlindeom.Fragment.BookFragment
import fisher.com.kotlindeom.Fragment.HomeFragment
import fisher.com.kotlindeom.Fragment.TravelStoryFragment
import fisher.com.kotlindeom.Fragment.ChildrenStoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three,R.string.tab_four)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        val fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(ChildrenStoryFragment())
        fragments.add(TravelStoryFragment())
        val nameList = nameResList.map(this::getString)

        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 4
        tabLayout.setupWithViewPager(viewPager)
    }

}
