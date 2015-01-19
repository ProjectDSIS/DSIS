package com.example.dsis;

import android.util.Log;

public class Schol_cus_View {
	private String []str = new String[5];

	   public Schol_cus_View(String st1, String st2, String st3, String st4,String st5)
	   {
		   Log.d("web","Schol_cus_View in 1");
	      this.str[0] = st1;
	      this.str[1] = st2;
	      this.str[2] = st3;
	      this.str[3] = st4;
	      this.str[4] = st5;
	      Log.d("web","Schol_cus_View in 2");
	   }


	    public String GetData(int index){
	        return str[index];
	    }
}
