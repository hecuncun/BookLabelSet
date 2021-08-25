package com.example.booklabelset

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.booklabelset.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var listTag = mutableListOf<Tag>()
    private var listSelectedTag = mutableListOf<Tag>()
    private val adapter by lazy {
        TagCloudViewAdapter(listTag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
        initListener()
        initView()
    }

    private fun initView() {
        mBinding.tagCloudView.setAdapter(adapter)
    }


    private fun initListener() {
        mBinding.tagCloudView.setOnTagClickListener { parent, view, position ->
          //  view.isSelected = !view.isSelected
            // 设置标签选中状态
            listTag[position].selected =!listTag[position].selected
            view.isSelected = listTag[position].selected
            if (view.isSelected) {
                if (listSelectedTag.size<5){
                    Log.e("TAG","SIZE = ${listSelectedTag.size}")
                    listSelectedTag.add(listTag[position])
                    val drawable = GradientDrawable()
                    drawable.setColor(Color.parseColor("#7187FF"))
                    drawable.cornerRadius = 21f.dp
                    view.setBackgroundDrawable(drawable)
                }else{
                    listTag[position].selected = false
                    view.isSelected = false
                    Toast.makeText(this, "最多选择5个", Toast.LENGTH_SHORT).show();
                    return@setOnTagClickListener
                }

            } else {
                listSelectedTag.remove(listTag[position])
            }
            Toast.makeText(this, "点击过的标签：$listSelectedTag", Toast.LENGTH_SHORT).show();
        }

        mBinding.tvChange.setOnClickListener {
            listTag.clear()
            listTag.addAll(listSelectedTag)
            for (i in 0..(11 - listSelectedTag.size)) {
                listTag.add(Tag("新标签$i", false))
            }
            adapter.notifyDataSetChanged()

        }
    }

    private fun initData() {
        for (i in 0..11) {
            listTag.add(Tag("标签$i", false))
        }
    }
}