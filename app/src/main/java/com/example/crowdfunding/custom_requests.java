package com.example.crowdfunding;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_requests extends BaseAdapter {
    String[]R_id,Org_lid,Name,Amount,Address1,Address2,Address3,Purpose,Date,Status,Organization_Name,remainingamnt;
    private Context context;

    public custom_requests(Context context, String[] R_id, String[] Org_lid, String[] Name, String[] Amount, String[] Address1, String[] Address2, String[] Address3, String[] Purpose, String[] Date, String[] Status, String[] Organization_Name, String[] remainingamnt)
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
        this.remainingamnt=remainingamnt;

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
            gridView=inflator.inflate(R.layout.custom_requests,null);

        }
        else
        {
            gridView=(View)view;

        }
//        TextView tv1=(TextView)gridView.findViewById(R.id.textView43);
//        TextView tv2=(TextView)gridView.findViewById(R.id.textView44);
//        TextView tv3=(TextView)gridView.findViewById(R.id.textView46);
//        TextView tv4=(TextView)gridView.findViewById(R.id.textView47);
        TextView tv1=(TextView)gridView.findViewById(R.id.textView39);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView33);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView35);
        TextView ramount=(TextView)gridView.findViewById(R.id.textView56);
//        ImageView img =gridView.findViewById(R.id.imageView5);
        Button PAY=gridView.findViewById(R.id.button9);


//        TextView tv6=(TextView)gridView.findViewById(R.id.textView32);
//        TextView tv7=(TextView)gridView.findViewById(R.id.textView34);
//        TextView tv8=(TextView)gridView.findViewById(R.id.textView36);
//        TextView tv9=(TextView)gridView.findViewById(R.id.textView38);







        tv1.setText(Name[i]);
        tv2.setText(Amount[i]);
        tv3.setText(Address1[i]);
        tv4.setText(Purpose[i]);
        tv5.setText(Date[i]);
        ramount.setText(remainingamnt[i]);

        float myamt=Float.parseFloat(Amount[i]);
        float remamt=Float.parseFloat(remainingamnt[i]);
        if(remamt>=myamt)
        {
            PAY.setVisibility(View.INVISIBLE);
        }
        else {
            PAY.setVisibility(View.VISIBLE);
        }

        PAY.setTag(i);
        PAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ik=Integer.parseInt(view.getTag().toString());

              float fs=  Float.parseFloat(Amount[ik])- Float.parseFloat(remainingamnt[ik]);



                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("rid",R_id[ik]);
                ed.putString("fs",fs+"");
//                ed.putString("maxamt",[ik]);
                ed.commit();
                Intent in=new Intent(context,payment_details.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });

//
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
////
//        String url="http://" + ip + ":5000"+photo[i];
//
//
//        Picasso.with(context).load(url).into(img);

        return gridView;
    }
}




