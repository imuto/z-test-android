package com.imuto.joy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imuto.joy.base.AbsRecyclerAdapter;
import com.imuto.joy.base.BaseActivity;
import com.imuto.joy.drag.DragHelperPage;
import com.imuto.joy.tipPage.TipsPage;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;

	private Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.mRecycler);

		layoutManager = new LinearLayoutManager(this);

		recyclerView.setLayoutManager(layoutManager);

		recyclerView.setHasFixedSize(true);
		adapter = new Adapter();

		adapter.setOnItemClickListener(new AbsRecyclerAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Item item = adapter.getItem(position);

				Intent intent = new Intent(MainActivity.this, item.toCls);

				startActivity(intent);
			}
		});

		recyclerView.setAdapter(adapter);

		initData();

	}

	private void initData() {

		ArrayList<Item> data = new ArrayList<>();
		Item item = new Item("拖拽子View", DragHelperPage.class);
		Item item2 = new Item("提示信息等", TipsPage.class);
		data.add(item);
		data.add(item2);

		adapter.setDataSet(data);
		adapter.notifyDataSetChanged();
	}

	private class Adapter extends AbsRecyclerAdapter<Item> {

		public Adapter() {
			super(MainActivity.this, new ArrayList<Item>(), R.layout.main_item);
		}

		@Override
		public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType, View view) {
			AbsViewHolder holder;
			holder = new NormalHolder(view, this);
			return holder;
		}

		@Override
		public void onBindItemViewHolder(AbsViewHolder viewHolder, Item data, int position, int realPosition) {
			OnBindNormalHolder((NormalHolder) viewHolder, data, position, realPosition);
		}

		private void OnBindNormalHolder(NormalHolder viewHolder, Item data, int position, int realPosition) {
			viewHolder.title.setText(data.title);
		}

		private class NormalHolder extends AbsViewHolder {

			TextView title;

			public NormalHolder(View itemView, AbsRecyclerAdapter adapter) {
				super(itemView, adapter);

				title = (TextView) itemView.findViewById(R.id.title);
			}
		}

	}

	private class Item {

		Item(String title, Class<? extends AppCompatActivity> toCls) {
			this.title = title;
			this.toCls = toCls;
		}

		private String title;
		private Class<? extends AppCompatActivity> toCls;
	}

}
