package com.example.booklabelset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.booklabelset.databinding.ActivityMainBinding
import com.example.booklabelset.randomlayout.FlyLayout
import com.example.booklabelset.randomlayout.RandomLayout

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding :ActivityMainBinding
    var tag1 = arrayOf(
        "校园剧情", "城市恋情", "言情小说", "武侠小说",
        "男生小说", "玄幻小说", "国际金融", "中国证券",
        "中国经营", "经济观察报", "中国经济网", "大年初一"
    )
    var tag2 = arrayOf(
        "FT中文网", "财经网", "创业家", "福布斯",
        "新财富杂志", "环球企业家", "中国证券报", "证券时报网",
        "商学院", "财新网", "华夏时报", "第一财经"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         mBinding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(mBinding.root)
         mBinding.flyout.setData(tag1)
         mBinding.flyout.setOnFlyEverythingListener(object :FlyLayout.OnFlyEverythingListener{
            override fun onItemClick(view: View?, position: Int, text: String?) {
                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationEnd(randomLayout: RandomLayout?, animationCount: Int) {

            }

        })
        mBinding.button.setOnClickListener {
            mBinding.flyout.setData(tag2)
        }
    }
}