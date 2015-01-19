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

public class Schol_cus_Adapter extends BaseAdapter {
	ArrayList<Schol_cus_View> items;
	LayoutInflater Inflater;  // 뷰를 xml을 이용하여 객체화 할때 사용
	Context context;
	int layout;
	
	public Schol_cus_Adapter(Context context, int layout, ArrayList<Schol_cus_View> items) {
		this.items = items;
		this.context = context;
		this.layout=layout;
		Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Schol_cus_ViewHolder viewHolder;
		 
	    // 캐시된 뷰가 없을 경우 새로 생성하고 뷰홀더를 생성한다
	    if(convertView == null)
	    {
	        convertView = Inflater.inflate(layout, parent, false);
	 
	        viewHolder = new Schol_cus_ViewHolder();
	        
	        for(int i = 0; i<viewHolder.t.length; i++){
                int id = convertView.getResources().getIdentifier("text"+(i+1), "id", "com.example.dsis");
                viewHolder.t[i] = (TextView) convertView.findViewById(id);
            }
	        
			Log.d("custom","check2");
			// 셋팅한 텍스트뷰의 텍스트에 이름과 전화번호를 넣어준다.
			
	        convertView.setTag(viewHolder);
	    }
	    // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
	    else
	    {
	        viewHolder = (Schol_cus_ViewHolder) convertView.getTag();
	    }
	 
	    for(int i =0; i<viewHolder.t.length; i++){
            viewHolder.t[i].setText(items.get(position).GetData(i));
        }
	    
	    if(viewHolder.t[2].length() == 4){ 		// "첫번째 커스텀뷰"는 푸른색
            for(int i =0; i<viewHolder.t.length; i++){
                viewHolder.t[i].setBackgroundColor(Color.parseColor("#d5ebde"));
            }
        }
        if(viewHolder.t[2].length() > 4){ 		// "장학내용 커스텀뷰"는 위에 첫줄은 회색으로
               viewHolder.t[0].setBackgroundColor(Color.parseColor("#eeeeee"));
               viewHolder.t[1].setBackgroundColor(Color.parseColor("#eeeeee"));
        }
        if(viewHolder.t[0].length() == 0){ 		// 맨마지막 "장학내역 합" 출력할땐 윗줄을 푸른색으로
            viewHolder.t[0].setBackgroundColor(Color.parseColor("#d5ebde"));
            viewHolder.t[1].setBackgroundColor(Color.parseColor("#d5ebde"));
     }
		return convertView;
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
