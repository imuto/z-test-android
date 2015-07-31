package com.imuto.joy.tipPage;

import android.os.Bundle;
import android.view.View;

import com.imuto.joy.R;
import com.imuto.joy.base.BaseActivity;

/**
 * Created by Naite.Zhou on 15/7/21.
 */
public class TipsPage extends BaseActivity {

	private PageTipsView yl_page_tip_view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tips);

		yl_page_tip_view = (PageTipsView) findViewById(R.id.yl_page_tip_view);

		findViewById(R.id.textView1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				yl_page_tip_view.tipShowNetworkError();
			}
		});

		findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				yl_page_tip_view.tipShowSingleText();
				yl_page_tip_view.tipSetSingleText("抱歉，没有找到相关内容！");
			}
		});

		findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				yl_page_tip_view.tipShowProgress();
			}
		});
	}
}
