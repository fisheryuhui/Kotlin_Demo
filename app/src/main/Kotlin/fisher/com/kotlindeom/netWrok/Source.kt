package fisher.com.kotlindeom.netWrok

import fisher.com.kotlindeom.Model.PictureStory

/**
 * Created by fisher on 2017/6/2.
 */
interface Source <T>{
    fun obtain(url: String) : ArrayList<T>
}