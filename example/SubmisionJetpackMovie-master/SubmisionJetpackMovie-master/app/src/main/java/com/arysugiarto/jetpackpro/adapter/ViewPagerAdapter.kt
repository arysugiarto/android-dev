package com.arysugiarto.jetpackpro.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.ui.favorite.FavoriteMovieFragment
import com.arysugiarto.jetpackpro.ui.favorite.FavoriteTvShowFragment

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}