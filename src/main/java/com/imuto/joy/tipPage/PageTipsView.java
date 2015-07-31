package com.imuto.joy.tipPage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.imuto.joy.R;

/**
 * 通用的页面状态View，包括loading动画，文本图片网络错误，单文本提示信息
 *
 * Created by Naite.Zhou on 15/7/30.
 */
public class PageTipsView extends FrameLayout {

	private ViewStub yl_vs_tips;
	private ViewStub yl_vs_progress;

	private LoadingView yl_layout_progress_layout;

	private View yl_layout_page_tip;
	private View yl_layout_img_txt;
	private YlTextView yl_tv_tip;
	private ImageView yl_iv_error_tip;
	private YlTextView yl_tv_error_tip;

	private View mSpecialView;

	public PageTipsView(Context context) {
		super(context);
		initView();
	}

	public PageTipsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PageTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.page_tips_view, this);

		setVisibility(View.INVISIBLE);

		yl_vs_tips = (ViewStub) findViewById(R.id.yl_vs_tips);
		yl_vs_progress = (ViewStub) findViewById(R.id.yl_vs_progress);
	}

	private void initProgress() {
		if (yl_layout_progress_layout == null) {
			yl_vs_progress.inflate();
			yl_layout_progress_layout = (LoadingView) findViewById(R.id.yl_layout_progress_layout);
		}
	}

	private void initTipView() {
		if (yl_layout_page_tip == null) {
			yl_vs_tips.inflate();
			yl_layout_page_tip = findViewById(R.id.yl_layout_page_tip);
			yl_layout_img_txt = findViewById(R.id.yl_layout_img_txt);
			yl_tv_tip = (YlTextView) yl_layout_page_tip.findViewById(R.id.yl_tv_tip);
			yl_tv_error_tip = (YlTextView) yl_layout_page_tip.findViewById(R.id.yl_tv_error_tip);
			yl_iv_error_tip = (ImageView) yl_layout_page_tip.findViewById(R.id.yl_iv_error_tip);
		}
	}

	private void hideProgress() {
		if (yl_layout_progress_layout != null) {
			yl_layout_progress_layout.setVisibility(View.INVISIBLE);
		}
	}

	private void hideTipView() {
		if (yl_layout_page_tip != null) {
			yl_layout_page_tip.setVisibility(View.INVISIBLE);
		}
	}

	private void hideSpecialView() {
		if (mSpecialView != null) {
			mSpecialView.setVisibility(View.INVISIBLE);
		}
	}

	private void showMe() {
		if (getVisibility() != View.VISIBLE) {
			setVisibility(View.VISIBLE);
			bringToFront();
		}
	}

	private void showProgress() {
		showMe();
		initProgress();
		hideTipView();
		hideSpecialView();
		yl_layout_progress_layout.setVisibility(View.VISIBLE);
	}

	private void showTipView() {
		showMe();

		initTipView();
		hideProgress();
		hideSpecialView();
		yl_layout_page_tip.setVisibility(View.VISIBLE);
	}

	private void showSpecialView() {
		showMe();

		hideProgress();
		hideTipView();
		if (mSpecialView != null) {
			mSpecialView.setVisibility(View.VISIBLE);
		}
	}

	public void tipShowProgress() {
		showProgress();
		yl_layout_progress_layout.setBackgroundColor(getResources().getColor(R.color.yl_background));
	}

	public void tipShowProgressTran() {
		showProgress();
		yl_layout_progress_layout.setBackgroundColor(getResources().getColor(R.color.transparent));
	}

	public void tipShowNetworkError() {
		showTipView();
		yl_layout_img_txt.setVisibility(View.VISIBLE);
		yl_tv_tip.setVisibility(View.INVISIBLE);
	}

	public void tipShowSingleText() {
		showTipView();
		yl_layout_img_txt.setVisibility(View.INVISIBLE);
		yl_tv_tip.setVisibility(View.VISIBLE);
	}

	public void tipShowSpecialView() {
		if (mSpecialView == null) {
			return;
		}

		showSpecialView();
	}

	public void tipHideView() {
		hideProgress();
		hideTipView();
		hideSpecialView();
		setVisibility(View.INVISIBLE);
	}

	public void tipSetTipView(View view) {
		if (view != null) {
			ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
			layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
			layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
			addView(view, layoutParams);
		} else {
			tipRemoveSpecialView();
		}
		mSpecialView = view;
	}

	public void tipRemoveSpecialView() {
		if (mSpecialView != null) {
			removeView(mSpecialView);
		}
	}

	public void tipSetSingleText(String msg) {
		if (yl_tv_tip == null) {
			initTipView();
		}
		yl_tv_tip.setText(msg);
	}

	public void tipSetText(String msg) {
		if (yl_tv_error_tip == null) {
			initTipView();
		}
		yl_tv_error_tip.setText(msg);
	}

	public void tipSetImg(int resId) {
		if (yl_iv_error_tip == null) {
			initTipView();
		}
		yl_iv_error_tip.setImageResource(resId);
	}

}
