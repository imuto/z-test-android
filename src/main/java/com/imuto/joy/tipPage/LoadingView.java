package com.imuto.joy.tipPage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.imuto.joy.utils.DensityUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

/**
 * Created by cuilongjun on 14-10-23.
 */
public class LoadingView extends LinearLayout {

	private boolean mIsDetached;

	private AnimatorSet animator1;
	private AnimatorSet animator2;
	private AnimatorSet animator3;
	private AnimatorSet animator4;
	private AnimatorSet animator5;

	public LoadingView(Context context) {
		this(context, null);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {

		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER);

		int color = Color.parseColor("#CD6456");

		int diameter = DensityUtil.dip2px(getContext(), 12);

		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		int color1 = Color.argb(178, r, g, b);
		int color2 = Color.argb(204, r, g, b);
		int color3 = Color.argb(255, r, g, b);

		int roundRadius = DensityUtil.dip2px(getContext(), diameter / 2); // 8dp
		// int strokeColor = Color.parseColor("#CD6456");
		// int fillColor = Color.parseColor("#CD6456");
		GradientDrawable bg1 = new GradientDrawable();
		bg1.setColor(color1);
		bg1.setCornerRadius(roundRadius);
		GradientDrawable bg2 = new GradientDrawable();
		bg2.setColor(color2);
		bg2.setCornerRadius(roundRadius);
		GradientDrawable bg3 = new GradientDrawable();
		bg3.setColor(color3);
		bg3.setCornerRadius(roundRadius);

		int margin = DensityUtil.dip2px(getContext(), 2);

		View item1 = new View(getContext());
		LayoutParams params = new LayoutParams(diameter, diameter);
		params.leftMargin = margin;
		params.rightMargin = margin;
		item1.setLayoutParams(params);

		item1.setBackgroundDrawable(bg1);

		View item2 = new View(getContext());
		params = new LayoutParams(diameter, diameter);
		params.leftMargin = margin;
		params.rightMargin = margin;
		item2.setLayoutParams(params);
		item2.setBackgroundDrawable(bg2);

		View item3 = new View(getContext());
		params = new LayoutParams(diameter, diameter);
		params.leftMargin = margin;
		params.rightMargin = margin;
		item3.setLayoutParams(params);
		item3.setBackgroundDrawable(bg3);

		View item4 = new View(getContext());
		params = new LayoutParams(diameter, diameter);
		params.leftMargin = margin;
		params.rightMargin = margin;
		item4.setBackgroundDrawable(bg2);
		item4.setLayoutParams(params);

		View item5 = new View(getContext());
		params = new LayoutParams(diameter, diameter);
		params.leftMargin = margin;
		params.rightMargin = margin;
		item5.setBackgroundDrawable(bg1);
		item5.setLayoutParams(params);

		addView(item1);
		addView(item2);
		addView(item3);
		addView(item4);
		addView(item5);

		// animator1 = getAnimator(item1, 0);
		// animator2 = getAnimator(item2, 150);
		// animator3 = getAnimator(item3, 250);
		// animator4 = getAnimator(item4, 350);
		// animator5 = getAnimator(item5, 450);

	}

	private AnimatorSet getAnimator(View item, int delay) {
		ObjectAnimator scalX = ObjectAnimator.ofFloat(item, "scaleX", 1, .3f);
		scalX.setDuration(500);
		scalX.setRepeatMode(ObjectAnimator.REVERSE);
		scalX.setRepeatCount(ObjectAnimator.INFINITE);

		ObjectAnimator scalY = ObjectAnimator.ofFloat(item, "scaleY", 1, .3f);
		scalY.setDuration(500);
		scalY.setRepeatMode(ObjectAnimator.REVERSE);
		scalY.setRepeatCount(ObjectAnimator.INFINITE);

		AnimatorSet set = new AnimatorSet();
		set.setDuration(500);
		set.setStartDelay(delay);
		set.playTogether(scalX, scalY);
		return set;
	}

	@Override
	public void setVisibility(int visibility) {
		if (visibility == VISIBLE) {
			super.setVisibility(visibility);
			startAnim();
		} else {
//			super.setVisibility(visibility);
			stopAnim(visibility);
		}
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
//        if (visibility == VISIBLE) {
//            startAnim();
//        } else {
//            stopAnim(visibility);
//        }
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mIsDetached = false;
		startAnim();
	}

	@Override
	protected void onDetachedFromWindow() {
		mIsDetached = true;
		stopAnim(View.GONE);
		super.onDetachedFromWindow();
	}

	private void startAnim() {
		if (mIsDetached) {
			return;
		}
		if (getVisibility() != VISIBLE) {
			return;
		}
		if (animator1 == null || (!animator1.isStarted() && !animator1.isRunning())) {
			freeAnim(getChildAt(0), animator1);
			animator1 = getAnimator(getChildAt(0), 0);
			animator1.start();
		}
		if (animator2 == null || (!animator2.isStarted() && !animator2.isRunning())) {
			freeAnim(getChildAt(1), animator2);
			animator2 = getAnimator(getChildAt(1), 150);
			animator2.start();
		}
		if (animator3 == null || (!animator3.isStarted() && !animator3.isRunning())) {
			freeAnim(getChildAt(2), animator3);
			animator3 = getAnimator(getChildAt(2), 250);
			animator3.start();
		}
		if (animator4 == null || (!animator4.isStarted() && !animator4.isRunning())) {
			freeAnim(getChildAt(3), animator4);
			animator4 = getAnimator(getChildAt(3), 350);
			animator4.start();
		}
		if (animator5 == null || (!animator5.isStarted() && !animator5.isRunning())) {
			freeAnim(getChildAt(4), animator5);
			animator5 = getAnimator(getChildAt(4), 450);
			animator5.start();
		}

		ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "alpha", 1);
		alpha.setDuration(0);
		alpha.start();
	}

	private void freeAnim(View view, AnimatorSet set) {
		if (set != null) {
			ArrayList<Animator> anims = set.getChildAnimations();
			if (anims != null) {
				for (Animator animator : anims) {
					ObjectAnimator oAnimator = (ObjectAnimator) animator;
					oAnimator.setRepeatCount(0);
					animator.cancel();
					animator.end();
				}
			}
			set.cancel();
			set.end();
		}
		if (view != null) {
			view.clearAnimation();
		}
	}

	@Override
	public void startAnimation(Animation animation) {
		// TODO Auto-generated method stub
		super.startAnimation(animation);
	}

	private void stopAnim(final int visibility) {
		if (animator1 != null) {
			freeAnim(getChildAt(0), animator1);
			animator1 = null;
		}
		if (animator2 != null) {
			freeAnim(getChildAt(1), animator2);
			animator2 = null;
		}
		if (animator3 != null) {
			freeAnim(getChildAt(2), animator3);
			animator3 = null;
		}
		if (animator4 != null) {
			freeAnim(getChildAt(3), animator4);
			animator4 = null;
		}
		if (animator5 != null) {
			freeAnim(getChildAt(4), animator5);
			animator5 = null;
		}

		ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "alpha", 1, .5f);
		alpha.addListener(
				new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {

					}

					@Override
					public void onAnimationEnd(Animator animation) {
						clearAnimation();
						LoadingView.super.setVisibility(View.GONE);
					}

					@Override
					public void onAnimationCancel(Animator animation) {

					}

					@Override
					public void onAnimationRepeat(Animator animation) {

					}
				}
		);
		alpha.setDuration(200);
		alpha.start();

	}

}
