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
	LayoutInflater Inflater;  // �並 xml�� �̿��Ͽ� ��üȭ �Ҷ� ���
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
		 
	    // ĳ�õ� �䰡 ���� ��� ���� �����ϰ� ��Ȧ���� �����Ѵ�
	    if(convertView == null)
	    {
	        convertView = Inflater.inflate(layout, parent, false);
	 
	        viewHolder = new Schol_cus_ViewHolder();
	        
	        for(int i = 0; i<viewHolder.t.length; i++){
                int id = convertView.getResources().getIdentifier("text"+(i+1), "id", "com.example.dsis");
                viewHolder.t[i] = (TextView) convertView.findViewById(id);
            }
	        
			Log.d("custom","check2");
			// ������ �ؽ�Ʈ���� �ؽ�Ʈ�� �̸��� ��ȭ��ȣ�� �־��ش�.
			
	        convertView.setTag(viewHolder);
	    }
	    // ĳ�õ� �䰡 ���� ��� ����� ��Ȧ���� ����Ѵ�
	    else
	    {
	        viewHolder = (Schol_cus_ViewHolder) convertView.getTag();
	    }
	 
	    for(int i =0; i<viewHolder.t.length; i++){
            viewHolder.t[i].setText(items.get(position).GetData(i));
        }
	    
	    if(viewHolder.t[2].length() == 4){ 		// "ù��° Ŀ���Һ�"�� Ǫ����
            for(int i =0; i<viewHolder.t.length; i++){
                viewHolder.t[i].setBackgroundColor(Color.parseColor("#d5ebde"));
            }
        }
        if(viewHolder.t[2].length() > 4){ 		// "���г��� Ŀ���Һ�"�� ���� ù���� ȸ������
               viewHolder.t[0].setBackgroundColor(Color.parseColor("#eeeeee"));
               viewHolder.t[1].setBackgroundColor(Color.parseColor("#eeeeee"));
        }
        if(viewHolder.t[0].length() == 0){ 		// �Ǹ����� "���г��� ��" ����Ҷ� ������ Ǫ��������
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
