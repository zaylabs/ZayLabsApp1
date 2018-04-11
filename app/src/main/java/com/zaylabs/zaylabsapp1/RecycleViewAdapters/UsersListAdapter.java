package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.content.Context;
import android.location.Address;
import com.mapbox.geocoder.android.AndroidGeocoder;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.GeoPoint;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.DTO.driverAvailable;
import com.zaylabs.zaylabsapp1.MainActivity;
import com.zaylabs.zaylabsapp1.R;
import com.zaylabs.zaylabsapp1.api.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    public List<customerRequest>cRequests;
    public List<driverAvailable> cDriverInfo;
    public Context context;
    private Location mCurrentLocation;

    public UsersListAdapter(Context context,List<customerRequest>cRequests){
        this.cRequests= cRequests;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        GeoPoint mPick = cRequests.get(position).getPickup();
        mCurrentLocation= ((MainActivity) context).mCurrentLocation;

        Location loc1 = new Location("");
        loc1.setLatitude(mCurrentLocation.getLatitude());
        loc1.setLongitude(mCurrentLocation.getLongitude());

        Location loc2 = new Location("");
        loc2.setLatitude(mPick.getLatitude());
        loc2.setLongitude(mPick.getLongitude());

        float distance = loc1.distanceTo(loc2)/1000;
        holder.mName.setText(cRequests.get(position).getName());
        holder.mPickup.setText(cRequests.get(position).getPickupaddress());
        holder.mDrop.setText(cRequests.get(position).getDropaddress());
        holder.mPhone.setText(cRequests.get(position).getPhone());
        holder.mRideDistance.setText(cRequests.get(position).getRidedistance());
        holder.mDiscription.setText(cRequests.get(position).getDescription());
        holder.mBoxes.setText(cRequests.get(position).getBoxes());
        holder.mWeight.setText(cRequests.get(position).getWeight());


        final String user_id = cRequests.get(position).userId;
        holder.mDistance.setText(String.valueOf(distance)+"KM");


        holder.mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cRequests.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cRequests.size());
                holder.itemView.setVisibility(View.GONE);
            }
        });
        holder.mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Accept Button",Toast.LENGTH_SHORT).show();
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(context,"User ID = " + user_id,Toast.LENGTH_SHORT).show();


            }
        });
    }


    @Override
    public int getItemCount() {
        return cRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView mName,mPickup,mDrop,mPhone, mDistance, mDiscription, mBoxes, mRideDistance,mWeight;
        public Button mAccept, mSkip;

        public ViewHolder(View itemView) {
            super(itemView);
            mView= itemView;
            mName=(TextView)mView.findViewById(R.id.name);
            mPickup=(TextView)mView.findViewById(R.id.pickup);
            mDrop=(TextView)mView.findViewById(R.id.drop);
            mPhone=(TextView)mView.findViewById(R.id.phone);
            mAccept=(Button)mView.findViewById(R.id.accept);
            mSkip=(Button)mView.findViewById(R.id.skip);
            mDistance=(TextView)mView.findViewById(R.id.distance);
            mRideDistance=(TextView)mView.findViewById(R.id.ride_distance);
            mDiscription=(TextView)mView.findViewById(R.id.description);
            mBoxes=(TextView)mView.findViewById(R.id.Boxes);
            mWeight=(TextView)mView.findViewById(R.id.weight);

        }
    }


}
