package com.example.crowdfunding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class custom_view_result extends BaseAdapter {
    String[] R_id,Org_lid,Name,Amount,Purpose,Date,Org_Name,Status;
    private Context context;

    public custom_view_result(Context context,String[]R_id,String[]Org_lid,String[]Name,String[]Amount,String[] Purpose,String[] Date,String[]Org_Name,String[]Status)
    {
        this.context=context;
        this.R_id=R_id;
        this.Org_lid=Org_lid;
        this.Name=Name;
        this.Amount=Amount;
        this.Purpose=Purpose;
        this.Date=Date;
        this.Org_Name=Org_Name;
        this.Status=Status;



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
            gridView=inflator.inflate(R.layout.custom_view_result, null);
//            gridView=inflator.inflate(R.layout.custom_view_reply,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.textView50);
        TextView tvamount=(TextView)gridView.findViewById(R.id.textView51);
        TextView tvpurpose=(TextView)gridView.findViewById(R.id.textView52);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView53);
        TextView tvorgname=(TextView)gridView.findViewById(R.id.textView54);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView55);



        tvdate.setText(Date[i]);
        tvname.setText(Name[i]);
        tvstatus.setText(Status[i]);
        tvamount.setText(Amount[i]);
        tvpurpose.setText(Purpose[i]);
        tvorgname.setText(Org_Name[i]);


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
