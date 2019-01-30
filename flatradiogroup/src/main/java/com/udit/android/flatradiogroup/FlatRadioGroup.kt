package com.udit.android.flatradiogroup

import android.content.Context
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.widget.CompoundButton
import android.widget.RadioButton

/**
 * Created by Udit on 08/05/18.
 */
class FlatRadioGroup : ConstraintHelper {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val radioViews = ArrayList<RadioButton>()
    private var skipCheckingViewsRecursively = false
    private var currentSelectedView: Int = -1
    private var viewSelectedBeforePreLayout: Int = -1

    private val childCheckChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (skipCheckingViewsRecursively) {
            return@OnCheckedChangeListener
        }
        if (currentSelectedView != -1) {
            skipCheckingViewsRecursively = true
            for (view in radioViews) {
                if (view.getId() == currentSelectedView) {
                    view.isChecked = false
                    break
                }
            }
            skipCheckingViewsRecursively = false
        }
        currentSelectedView = buttonView.id
    }


    override fun init(attrs: AttributeSet?) {
        super.init(attrs)
        this.mUseViewMeasure = false
    }

    override fun updatePreLayout(container: ConstraintLayout) {
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val view = container.getViewById(id)
            if (view != null && view is RadioButton) {
                radioViews.add(view)
                view.setOnCheckedChangeListener(childCheckChangeListener)
            }
        }

        if (viewSelectedBeforePreLayout != -1) selectViewProgramatically(viewSelectedBeforePreLayout)
    }

    override fun updatePostLayout(container: ConstraintLayout?) {
        val params = this.layoutParams as ConstraintLayout.LayoutParams
        params.width = 0
        params.height = 0
    }

    fun clearSelection() {
        if (currentSelectedView != -1) {
            skipCheckingViewsRecursively = true
            for (view in radioViews) {
                if (view.getId() == currentSelectedView) {
                    view.isChecked = false
                    break
                }
            }
            skipCheckingViewsRecursively = false
            currentSelectedView = -1
        }
    }

    fun selectViewProgramatically(buttonViewId: Int) {

        viewSelectedBeforePreLayout = if (radioViews.isEmpty()) {
            buttonViewId
        } else -1

        skipCheckingViewsRecursively = true
        for (view in radioViews) {
            view.isChecked = view.id == buttonViewId
        }
        skipCheckingViewsRecursively = false

        currentSelectedView = buttonViewId

    }
}