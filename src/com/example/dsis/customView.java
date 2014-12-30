package com.example.dsis;

public class customView {
    private String []str = new String[6];

	public customView(String st1, String st2, String st3, String st4, String st5, String st6)
	{
        this.str[0] = st1;
        this.str[1] = st2;
        this.str[2] = st3;
        this.str[3] = st4;
        this.str[4] = st5;
        this.str[5] = st6;

	}

    public String getString(int _index)
    {
        return str[_index];
    }
}
