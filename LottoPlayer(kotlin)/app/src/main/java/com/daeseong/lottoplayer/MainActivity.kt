package com.daeseong.lottoplayer

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.lottoplayer.Database.DbHandler
import com.daeseong.lottoplayer.Database.Lotto
import com.daeseong.lottoplayer.Database.LottoTop
import com.daeseong.lottoplayer.Util.FloatingTextView
import com.daeseong.lottoplayer.Util.Lottoutil.dip2px
import com.daeseong.lottoplayer.Util.RecycleUtil.recursiveRecycle

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var mainActivity: MainActivity? = null
    fun getMainActivity(): MainActivity? {
        return mainActivity
    }

    private fun setMainActivity(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    private var toolbar: androidx.appcompat.widget.Toolbar? = null
    private var tabLayout: com.google.android.material.tabs.TabLayout? = null
    private var viewPager: androidx.viewpager.widget.ViewPager? = null
    private var lottoAdapter: LottoAdapter? = null

    private var floatingTextView: FloatingTextView? = null

    private var dbHandler: DbHandler? = null
    private var mLotto_list: MutableList<Lotto>? = null
    private var mLottoTop_list: MutableList<LottoTop>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setMainActivity(this)

        InitTitleBar()

        setContentView(R.layout.activity_main);

        InitData()

        init()

        InitFloating()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            mLotto_list!!.clear()
            mLottoTop_list!!.clear()
            recursiveRecycle(window.decorView)
            System.gc()
        } catch (e: Exception) {
        }
    }

    private fun InitData() {

        dbHandler = DbHandler.getInstance(this)
        mLotto_list = ArrayList()
        mLotto_list = dbHandler!!.getLotto()
        mLottoTop_list = ArrayList()
        mLottoTop_list = dbHandler!!.getLottoTop()
    }

    private fun InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.statusbar_bg)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    private fun InitFloating() {

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val screenLength: Int = size.y
        val rootView = findViewById<ViewGroup>(R.id.main1)
        floatingTextView = FloatingTextView(rootView)
        floatingTextView!!.getFloatingview()!!.visibility = View.VISIBLE
        val layoutParams = floatingTextView!!.getFloatingview()!!.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.setMargins(0, screenLength - dip2px(this, 60f), 0, 0)
        floatingTextView!!.getFloatingview()!!.layoutParams = layoutParams
        rootView.addView(floatingTextView!!.getFloatingview())
        setFloating()
    }

    private fun setFloating() {

        try {
            floatingTextView!!.setText1("순차 번호별 누적횟수", Color.parseColor("#ff9900"))
            val sMsg2 = String.format("%d %d %d %d %d %d", dbHandler!!.getPartTop(1), dbHandler!!.getPartTop(2), dbHandler!!.getPartTop(3), dbHandler!!.getPartTop(4), dbHandler!!.getPartTop(5), dbHandler!!.getPartTop(6))
            floatingTextView!!.setText2(sMsg2, Color.parseColor("#ff9900"))
        } catch (e: java.lang.Exception) {
        }
    }

    private fun init() {

        //toolbar
        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar);

        //viewPager
        viewPager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewpager)
        if (viewPager != null) {
            setViewPager(viewPager!!)
        }

        //tab
        tabLayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setViewPager(viewPager: ViewPager) {

        lottoAdapter = LottoAdapter(supportFragmentManager)
        lottoAdapter!!.addFragment(MainTab1Fragment(), "당첨번호")
        lottoAdapter!!.addFragment(MainTab2Fragment(), "누적횟수")
        lottoAdapter!!.addFragment(MainTab3Fragment(), "등록")
        lottoAdapter!!.addFragment(MainTab4Fragment(), "나눔로또")
        viewPager.adapter = lottoAdapter

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    private var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            hideKeyboard()
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    fun hideKeyboard() {
        try {
            if (currentFocus != null) {
                val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun getLotto(): List<Lotto?>? {
        return mLotto_list
    }

    fun getLottoTop(): List<LottoTop?>? {
        return mLottoTop_list
    }

    fun reloadLotto() {

        try {
            mLotto_list!!.clear()
            mLotto_list = dbHandler!!.getLotto()
        } catch (e: java.lang.Exception) {
        }
    }

    fun addLotto(rIndex: Int, Date: String?, Part1: Int, Part2: Int, Part3: Int, Part4: Int, Part5: Int, Part6: Int, Bonus: Int) {

        try {
            if (!dbHandler!!.isExistData(rIndex)) {
                val lotto = Lotto(rIndex, Date!!, Part1, Part2, Part3, Part4, Part5, Part6, Bonus)
                dbHandler!!.addLotto(lotto)
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun deleteLotto(rIndex: Int) {
        try {
            if (dbHandler!!.isExistData(rIndex)) {
                dbHandler!!.deleteLotto(rIndex)
            }
        } catch (e: java.lang.Exception) {
        }
    }
}
