package com.example.booklabelset

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moxun.tagcloudlib.view.TagsAdapter

val Float.dp
    get() = DestinyUtils.fromDp(this)

val Float.sp
    get() = DestinyUtils.fromSp(this)

val Int.dp
    get() = DestinyUtils.fromDp(this.toFloat()).toInt()

val Int.sp
    get() = DestinyUtils.fromSp(this.toFloat()).toInt()

class TagCloudViewAdapter(val list: List<Tag>) : TagsAdapter() {


    override fun getCount(): Int = list.size

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
        val tvTag = View.inflate(context, R.layout.item_tag, null) as TextView
        tvTag.text = getItem(position)
        tvTag.isSelected = list[position].selected
        return tvTag
    }

    override fun getItem(position: Int): String = list[position].name

    override fun getPopularity(position: Int): Int = position % 7

    override fun onThemeColorChanged(view: View?, themeColor: Int) {
        // view?.setBackgroundColor(themeColor)
        view?.run {
            if (view.isSelected) {
                val drawable = GradientDrawable()
                drawable.setColor(Color.parseColor("#7187FF"))
                drawable.cornerRadius = 21f.dp
                view.setBackgroundDrawable(drawable)
            } else {
                val drawable = GradientDrawable()
                drawable.setColor(themeColor)
                drawable.cornerRadius = 21f.dp
                view.setBackgroundDrawable(drawable)
            }
        }

    }
}