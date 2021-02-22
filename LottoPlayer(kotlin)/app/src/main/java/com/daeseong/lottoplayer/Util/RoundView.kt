package com.daeseong.lottoplayer.Util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


open class RoundView : View {

    companion object {
        private val DEFAULT_TITLE_COLOR: Int = android.graphics.Color.WHITE
        private val DEFAULT_BACKGROUND_COLOR: Int = android.graphics.Color.CYAN
        private const val DEFAULT_VIEW_SIZE = 80
        private const val DEFAULT_TITLE_SIZE = 25f
        private const val DEFAULT_TITLE = "0"
    }

    private var mTitleColor = DEFAULT_TITLE_COLOR
    private var mBackgroundColor = DEFAULT_BACKGROUND_COLOR
    private var mTitleText = DEFAULT_TITLE
    private var mTitleSize = DEFAULT_TITLE_SIZE

    private var mTitleTextPaint: TextPaint? = null
    private var mBackgroundPaint: Paint? = null
    private var mInnerRectF: RectF? = null
    private var mViewSize = 0

    private var mFont = Typeface.defaultFromStyle(Typeface.NORMAL)

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        //Title TextPaint
        mTitleTextPaint = TextPaint()
        mTitleTextPaint!!.flags = Paint.ANTI_ALIAS_FLAG
        mTitleTextPaint!!.typeface = mFont
        mTitleTextPaint!!.textAlign = Paint.Align.CENTER
        mTitleTextPaint!!.isLinearText = true
        mTitleTextPaint!!.color = mTitleColor
        mTitleTextPaint!!.textSize = mTitleSize

        //Background Paint
        mBackgroundPaint = Paint()
        mBackgroundPaint!!.flags = Paint.ANTI_ALIAS_FLAG
        mBackgroundPaint!!.style = Paint.Style.FILL
        mBackgroundPaint!!.color = mBackgroundColor
        mInnerRectF = RectF()
    }

    open fun invalidateTextPaints() {
        mTitleTextPaint!!.typeface = mFont
        mTitleTextPaint!!.textSize = mTitleSize
        mTitleTextPaint!!.color = mTitleColor
    }

    open fun invalidatePaints() {
        mBackgroundPaint!!.color = mBackgroundColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec)
        val height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec)
        mViewSize = width.coerceAtMost(height)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        mInnerRectF!![0f, 0f, mViewSize.toFloat()] = mViewSize.toFloat()
        mInnerRectF!!.offset((width - mViewSize) / 2.toFloat(), (height - mViewSize) / 2.toFloat())
        val centerX = mInnerRectF!!.centerX()
        val centerY = mInnerRectF!!.centerY()
        val xPos = centerX.toInt()
        val yPos = (centerY - (mTitleTextPaint!!.descent() + mTitleTextPaint!!.ascent()) / 2).toInt()
        canvas.drawOval(mInnerRectF!!, mBackgroundPaint!!)
        canvas.drawText(mTitleText, xPos.toFloat(), yPos.toFloat(), mTitleTextPaint!!)
    }

    open fun getTitleText(): String? {
        return mTitleText
    }

    open fun setTitleText(title: String) {
        mTitleText = title
        invalidate()
    }

    open fun getBackgroundColor(): Int {
        return mBackgroundColor
    }

    override fun setBackgroundColor(backgroundColor: Int) {
        mBackgroundColor = backgroundColor
        invalidatePaints()
    }

    open fun getTitleSize(): Float {
        return mTitleSize
    }

    open fun setTitleSize(titleSize: Float) {
        mTitleSize = titleSize
        invalidateTextPaints()
    }

    open fun setTextTypeface(font: Typeface?) {
        mFont = font
        invalidateTextPaints()
    }

    open fun setTitleColor(titleColor: Int) {
        mTitleColor = titleColor
        invalidateTextPaints()
    }
}