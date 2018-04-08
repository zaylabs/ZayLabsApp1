package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;

import java.util.ArrayList;
import java.util.List;


public class Customadapternew extends BaseAdapter {
    TextView mName, mPickup, mDrop, mPhone;
    Context context;
    private ArrayList<customerRequest> list;


    public Customadapternew(ArrayList<customerRequest> list, Context con) {
        this.list = list;
        this.context = con;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.job_list_single_item, null);

        mName=v.findViewById(R.id.tv_customerName);
        mPickup=v.findViewById(R.id.tv_pickup_location);
        mDrop=v.findViewById(R.id.tv_drop_location);
        mPhone=v.findViewById(R.id.tv_phone);


        mName.setText(list.get(position).getName());
        mPickup.setText(list.get(position).getPickup().toString());
        mDrop.setText(list.get(position).getDrop().toString());
        mPhone.setText(list.get(position).getPhone());

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(context, list.get(position).getName()+"Perform Action", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }
}
