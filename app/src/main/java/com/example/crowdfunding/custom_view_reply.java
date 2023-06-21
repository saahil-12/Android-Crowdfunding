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


public class custom_view_reply extends BaseAdapter {
    String []Complaint_id,User_lid,Date,Reply,Status,Complaint;
    private Context context;

    public custom_view_reply(Context context,String[]Complaint_id,String[]User_lid,String[]Date,String[]Reply,String[] Status,String[] Complaint)
    {
        this.context=context;
        this.Complaint_id=Complaint_id;
        this.User_lid=User_lid;
        this.Date=Date;
        this.Reply=Reply;
        this.Status=Status;
        this.Complaint=Complaint;



    }

    @Override
    public int getCount() {
        return Complaint_id.length;
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
            gridView=inflator.inflate(R.layout.custom_view_reply,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView18);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView20);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView23);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView42);



        tv1.setText(Date[i]);
        tv2.setText(Complaint[i]);
        tv3.setText(Status[i]);
        tv4.setText(Reply[i]);


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
