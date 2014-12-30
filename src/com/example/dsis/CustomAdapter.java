package com.example.dsis;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter  {
	ArrayList<customView> items;
	LayoutInflater Inflater;  // 뷰를 xml을 이용하여 객체화 할때 사용
	Context context;
	int layout;
	
	public CustomAdapter(Context context, int layout, ArrayList<customView> items) {
		this.items = items;
		this.context = context;
		this.layout=layout;
		Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CustomViewHolder viewHolder;
		 
	    // 캐시된 뷰가 없을 경우 새로 생성하고 뷰홀더를 생성한다
	    if(convertView == null)
	    {
	        convertView = Inflater.inflate(layout, parent, false);
	 
	        viewHolder = new CustomViewHolder();
	        
	        viewHolder.t1 = (TextView)convertView.findViewById(R.id.text1);  // 뷰의 ui와 뷰홀더를 연결
	        viewHolder.t2 = (TextView)convertView.findViewById(R.id.text2);
	        viewHolder.t3 = (TextView)convertView.findViewById(R.id.text3);
	        viewHolder.t4 = (TextView)convertView.findViewById(R.id.text4);
	        viewHolder.t5 = (TextView)convertView.findViewById(R.id.text5);
	        viewHolder.t6 = (TextView)convertView.findViewById(R.id.text6);
	        
			Log.d("custom","check2");
			// 셋팅한 텍스트뷰의 텍스트에 이름과 전화번호를 넣어준다.
			
	        convertView.setTag(viewHolder);
	    }
	    // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
	    else
	    {
	        viewHolder = (CustomViewHolder) convertView.getTag();
	    }
	 
	    viewHolder.t1.setText(items.get(position).st1_r());
	    viewHolder.t2.setText(items.get(position).st2_r());
	    viewHolder.t3.setText(items.get(position).st3_r());
	    viewHolder.t4.setText(items.get(position).st4_r());
	    viewHolder.t5.setText(items.get(position).st5_r());
	    viewHolder.t6.setText(items.get(position).st6_r());
	    
	    if(viewHolder.t1.length()==2){ // 첫번쨰 줄은 녹색?? 으로 해줌
			viewHolder.t1.setBackgroundColor(Color.parseColor("#d5ebde"));
			viewHolder.t2.setBackgroundColor(Color.parseColor("#d5ebde"));
			viewHolder.t3.setBackgroundColor(Color.parseColor("#d5ebde"));
			viewHolder.t4.setBackgroundColor(Color.parseColor("#d5ebde"));
			viewHolder.t5.setBackgroundColor(Color.parseColor("#d5ebde"));
			viewHolder.t6.setBackgroundColor(Color.parseColor("#d5ebde"));
		}
		if(viewHolder.t1.length()==4){  // 년도 학기가 표시되는 줄은 회색으로 해줌
			viewHolder.t1.setBackgroundColor(Color.parseColor("#eeeeee"));
			viewHolder.t2.setBackgroundColor(Color.parseColor("#eeeeee"));
			viewHolder.t3.setBackgroundColor(Color.parseColor("#eeeeee"));
			viewHolder.t4.setBackgroundColor(Color.parseColor("#eeeeee"));
			viewHolder.t5.setBackgroundColor(Color.parseColor("#eeeeee"));
			viewHolder.t6.setBackgroundColor(Color.parseColor("#eeeeee"));
		}
		if(viewHolder.t1.length()==0){  // 년도 학기가 표시되는 줄은 회색으로 해줌
			viewHolder.t1.setBackgroundColor(Color.parseColor("#ffffff"));
			viewHolder.t2.setBackgroundColor(Color.parseColor("#ffffff"));
			viewHolder.t3.setBackgroundColor(Color.parseColor("#ffffff"));
			viewHolder.t4.setBackgroundColor(Color.parseColor("#ffffff"));
			viewHolder.t5.setBackgroundColor(Color.parseColor("#ffffff"));
			viewHolder.t6.setBackgroundColor(Color.parseColor("#ffffff"));
		}
		
		return convertView;
/*
		View v = convertView;			

		if ( v == null ) {
			v = Inflater.inflate(layout, parent ,false);
		}
		customView p = items.get(position);
		
		Log.d("custom","check1");
		if ( p != null )
		{
			// 2개의 텍스트뷰를 셋팅해준다.
			TextView t1 = (TextView)v.findViewById(R.id.text1);
			TextView t2 = (TextView)v.findViewById(R.id.text2);
			TextView t3 = (TextView)v.findViewById(R.id.text3);
			TextView t4 = (TextView)v.findViewById(R.id.text4);
			TextView t5 = (TextView)v.findViewById(R.id.text5);
			TextView t6 = (TextView)v.findViewById(R.id.text6);

			Log.d("custom","check2");
			// 셋팅한 텍스트뷰의 텍스트에 이름과 전화번호를 넣어준다.
			
			t1.setText(items.get(position).st1_r());
			t2.setText(items.get(position).st2_r());
			t3.setText(items.get(position).st3_r());
			t4.setText(items.get(position).st4_r());
			t5.setText(items.get(position).st5_r());
			t6.setText(items.get(position).st6_r());
			
//			if(t2.getText().length()==2){ // 첫번쨰 줄은 녹색?? 으로 해줌
//				t1.setBackgroundColor(Color.parseColor("#d5ebde"));
//				t2.setBackgroundColor(Color.parseColor("#d5ebde"));
//				t3.setBackgroundColor(Color.parseColor("#d5ebde"));
//				t4.setBackgroundColor(Color.parseColor("#d5ebde"));
//				t5.setBackgroundColor(Color.parseColor("#d5ebde"));
//				t6.setBackgroundColor(Color.parseColor("#d5ebde"));
//			}
//			if(t2.getText().length()==4){  // 년도 학기가 표시되는 줄은 회색으로 해줌
//				t1.setBackgroundColor(Color.parseColor("#eeeeee"));
//				t2.setBackgroundColor(Color.parseColor("#eeeeee"));
//				t3.setBackgroundColor(Color.parseColor("#eeeeee"));
//				t4.setBackgroundColor(Color.parseColor("#eeeeee"));
//				t5.setBackgroundColor(Color.parseColor("#eeeeee"));
//				t6.setBackgroundColor(Color.parseColor("#eeeeee"));
//			}

		}
		Log.d("custom","check3");
		return v;*/
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
