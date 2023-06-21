package com.example.crowdfunding;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.BaseAdapter;

public class custom_view_fund_requirements extends BaseAdapter {
    String[]R_id,Org_lid,Name,Amount,Address1,Address2,Address3,Purpose,Date,Status,Organization_Name;
    private Context context;

    public custom_view_fund_requirements(Context context,String[] R_id,String[] Org_lid,String[] Name,String[] Amount,String[] Address1,String[] Address2,String[] Address3,String[] Purpose,String[] Date,String[] Status,String[] Organization_Name)
    {
        this.context=context;
        this.R_id=R_id;
        this.Org_lid=Org_lid;
        this.Name=Name;
        this.Amount=Amount;
        this.Address1=Address1;
        this.Address2=Address2;
        this.Address3=Address3;
        this.Purpose=Purpose;
        this.Date=Date;
        this.Status=Status;
        this.Organization_Name=Organization_Name;

    }

    @Override
    public int getCount() {
        return R_id.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_view_fund_requirements,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView40);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView24);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView28);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView30);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView32);
        TextView tv7=(TextView)gridView.findViewById(R.id.textView34);
        TextView tv8=(TextView)gridView.findViewById(R.id.textView36);
        TextView tv9=(TextView)gridView.findViewById(R.id.textView38);





        tv1.setText(Name[i]);
        tv2.setText(Organization_Name[i]);
        tv3.setText(Amount[i]);
        tv4.setText(Address1[i]);
        tv5.setText(Address2[i]);
        tv6.setText(Address3[i]);
        tv7.setText(Purpose[i]);
        tv8.setText(Date[i]);
        tv9.setText(Status[i]);


//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":5000/static/game/"+gamecode[i]+".jpg";
//
//
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}




