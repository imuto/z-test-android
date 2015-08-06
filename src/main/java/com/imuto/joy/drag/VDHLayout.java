package com.imuto.joy.drag;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Naite.Zhou on 15/7/21.
 */
public class VDHLayout extends LinearLayout {

	private ViewDragHelper dragHelper;

	public VDHLayout(Context context) {
		super(context);
		initView();
	}

	public VDHLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public VDHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				return true;
			}

			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {

				final int leftBound = getPaddingLeft();
				final int rightBound = getWidth() - child.getWidth() - leftBound;

				final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

				return newLeft;

			}

			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				final int topBound = getPaddingTop();
				final int bottomBound = getHeight() - child.getHeight() - topBound;

				final int newLeft = Math.min(Math.max(top, topBound), bottomBound);

				return newLeft;
			}
		});
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return dragHelper.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		dragHelper.processTouchEvent(event);
		return true;
	}
}
