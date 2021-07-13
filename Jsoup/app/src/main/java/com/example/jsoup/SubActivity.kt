//package com.example.jsoup
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.univofseoul_meal.Adapter.TabViewpagerAdapter
//import com.example.univofseoul_meal.Utils.WebCrawlingUtil
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.MobileAds
//import kotlinx.android.synthetic.main.activity_sub.*
//
//class SubActivity : AppCompatActivity() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sub)
//
//        //툴바 설정
//        setSupportActionBar(toolbar_subActivity)
//        supportActionBar?.title = ""
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        //MainActivity에서 건너온 건물이름과 건물에 해당하는 학식 주소이름
//        val intent = this.intent
//        val buildingName = intent.getStringExtra("buildingName").toString()
//        textview_subActivity.text = buildingName
//
//        val address = intent.getStringExtra("address").toString()
//
//        val link = WebCrawlingUtil.getLink(address)
//
//        initTabViewpager(link)
//        adviewSetting()
//    }
//
//    private fun initTabViewpager(link : String) {
//        val adapter = TabViewpagerAdapter(supportFragmentManager, link)
//        viewpager_subActivity.adapter = adapter
//        tabLayout_subActivity.setupWithViewPager(viewpager_subActivity)
//        tabLayout_subActivity.getTabAt(0)?.setIcon(R.drawable.ic_rice)
//        tabLayout_subActivity.getTabAt(1)?.setIcon(R.drawable.ic_lunch)
//        tabLayout_subActivity.getTabAt(2)?.setIcon(R.drawable.ic_dinner)
//    }
//    private fun adviewSetting(){
//        MobileAds.initialize(this, getString(R.string.admob_app_id))
//
//        val mAdView = findViewById(R.id.adview_subActivity) as AdView
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
//    }
//}
