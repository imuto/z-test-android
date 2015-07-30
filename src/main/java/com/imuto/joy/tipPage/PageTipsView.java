package com.imuto.joy.tipPage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.FrameLayout;

/**
 * Created by Naite.Zhou on 15/7/30.
 */
public class PageTipsView extends FrameLayout {

	private ViewStub yl_vs_tips;
	private ViewStub yl_vs_progress;

	public PageTipsView(Context context) {
		super(context);
	}

	public PageTipsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PageTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void tipShowProgress() {

	}

	public void tipShowProgressTran() {

	}

	public void tipShowNetworkError() {

	}

	public void tipShow(String msg) {

	}

}
