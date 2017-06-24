package fisher.com.kotlindeom.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by fisher on 2017/6/2.
 */
class ContentPagerAdapter(val fragments: List<Fragment>, val titles: List<String>,val fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int  = fragments.size

    override fun getPageTitle(position: Int): CharSequence  = titles[position]

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }
}