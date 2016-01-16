package com.example.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpendedTest extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_list);
		ExpandableListAdapter adapter = new BaseExpandableListAdapter()
		{
			int[] logos = new int[]{R.drawable.kula, R.drawable.right};
			private String[] armTypes = new String[]{"kula", "right"};
			private String[][] arms = new String[][]{{"a", "b"}, {"d", "e"}};

			@Override
			public Object getChild(int arg0, int arg1) {
				return arms[arg0][arg1];  //arg0 groupPosition arg1 childPosition
			}

			@Override
			public long getChildId(int arg0, int arg1) {
				return arg1;
			}

			@Override
			public View getChildView(int arg0, int arg1, boolean arg2,
					View arg3, ViewGroup arg4) {
				TextView text = getTextView();
				text.setText(getChild(arg0, arg1).toString());
				return text;
			}

			private TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
				TextView textView = new TextView(ExpendedTest.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			@Override
			public int getChildrenCount(int arg0) {
				return arms[arg0].length;
			}

			@Override
			public Object getGroup(int arg0) {
				return armTypes[arg0];
			}

			@Override
			public int getGroupCount() {
				return armTypes.length;
			}

			@Override
			public long getGroupId(int arg0) {
				return arg0;
			}

			@Override
			public View getGroupView(int arg0, boolean arg1, View arg2,
					ViewGroup arg3) {
				LinearLayout ll = new LinearLayout(ExpendedTest.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(ExpendedTest.this);
				logo.setImageResource(logos[arg0]);
				ll.addView(logo);
				TextView text = getTextView();
				text.setText(getGroup(arg0).toString());
				ll.addView(text);
				return ll;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public boolean isChildSelectable(int arg0, int arg1) {
				return true;
			}
			
		};
		ExpandableListView expandListView = (ExpandableListView)findViewById(R.id.expand_list);
		expandListView.setAdapter(adapter);
	}
}