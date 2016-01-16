package com.example.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class myAdpTest extends ListActivity {


	private List<Map<String, Object>> mData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Alice");
		map.put("info", "first");
		map.put("listImage", android.R.drawable.alert_dark_frame);
		map.put("btnList", "confirmA");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "Cherry");
		map.put("info", "second");
		map.put("listImage", android.R.drawable.alert_light_frame);
		map.put("btnList", "confirmB");
		list.add(map);
		
		return list;
	}
	
	// ListView 中某项被选中后的逻辑
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Log.v("MyListView4-click", (String)mData.get(position).get("title"));
		Toast.makeText(getApplicationContext(), "MyListView4-click", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo(){
		new AlertDialog.Builder(this)
		.setTitle("我的listview")
		.setMessage("介绍...")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
		
	}
	
	
	
	public final class ViewHolder{
		public ImageView listImage;
		public TextView name;
		public TextView info;
		public Button btnList;
	}
	
	
	public class MyAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.sim_adp_layout, null);
				holder.listImage = (ImageView)convertView.findViewById(R.id.listImage);
				holder.name = (TextView)convertView.findViewById(R.id.name);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.btnList = (Button)convertView.findViewById(R.id.btnList);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			
			holder.listImage.setBackgroundResource((Integer)mData.get(position).get("listImage"));
			holder.name.setText((String)mData.get(position).get("name"));
			holder.info.setText((String)mData.get(position).get("info"));
			
			holder.btnList.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showInfo();					
				}
			});
			
			
			return convertView;
		}
		
	}
	
	
	
	
}
