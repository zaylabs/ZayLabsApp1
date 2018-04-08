package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<customerRequest> {

    private TextView mName, mPickup, mDrop, mPhone;

    public UsersAdapter(Context context, ArrayList<customerRequest>requests){
        super(context,0,requests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        customerRequest request = getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.job_list_single_item,parent,false);
                    }
        mName=(TextView)convertView.findViewById(R.id.tv_name);
        mPickup=(TextView)convertView.findViewById(R.id.tv_pickup_location);
        mDrop=(TextView)convertView.findViewById(R.id.tv_drop_location);
        mPhone=(TextView)convertView.findViewById(R.id.tv_phone);

        return convertView;
    }
}

