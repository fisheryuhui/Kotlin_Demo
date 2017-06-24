package fisher.com.kotlindeom.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.snackbar
import kotlinx.android.synthetic.main.fragment_comic_page.*
import org.jetbrains.anko.find

/**
 * Created by fisher on 2017/6/7.
 */
class ComicFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var ic_comic: ImageView
    lateinit var url: String

    companion object {
        fun newInstance(url: String): ComicFragment {
            val args: Bundle = Bundle()
            args.putString("url", url)
            val fragment: ComicFragment = newInstance()
            fragment.arguments = args
            return fragment
        }

        fun newInstance(): ComicFragment {
            return ComicFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.reenterTransition = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_comic_page, container, false)
        progressBar = rootView.find(R.id.progressBar)
        ic_comic = rootView.find(R.id.iv_comic)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            url = arguments.getString("url")
        }
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.material_deep_purple_50)
                .into(ic_comic, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError() {
                        iv_comic.snackbar(R.string.network_error)
                    }

                })
    }
}