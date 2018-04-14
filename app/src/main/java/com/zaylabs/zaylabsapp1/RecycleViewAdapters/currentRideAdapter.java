package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zaylabs.zaylabsapp1.DTO.Userid;
import com.zaylabs.zaylabsapp1.DTO.acceptRequest;
import com.zaylabs.zaylabsapp1.DTO.driverHistory;
import com.zaylabs.zaylabsapp1.R;
import com.zaylabs.zaylabsapp1.current_Ride;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class currentRideAdapter extends RecyclerView.Adapter<currentRideAdapter.ViewHolder> {

private final Context context;
private final List<acceptRequest> dHistory;

private FirebaseFirestore db;
private FirebaseAuth mAuth;
private String userID;
private String UniqueID;
    private Date date =  Calendar.getInstance().getTime();

    public currentRideAdapter(Context context, List<acceptRequest> dHistory){

        this.context = context;
        this.dHistory = dHistory;
        }

@NonNull
@Override
public currentRideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_ride_siglet_item,parent,false);
        return new currentRideAdapter.ViewHolder(view);


        }

@Override
public void onBindViewHolder(@NonNull final currentRideAdapter.ViewHolder holder, final int position) {

    db = FirebaseFirestore.getInstance();

    mAuth = FirebaseAuth.getInstance();

    userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


    holder.mName.setText(dHistory.get(position).getName());
        holder.mPickup.setText(dHistory.get(position).getPickupaddress());
        holder.mDrop.setText(dHistory.get(position).getDropaddress());
        holder.mPhone.setText(dHistory.get(position).getPhone());
        holder.mstatus.setText(dHistory.get(position).getStatus());
        holder.mRideDistance.setText(dHistory.get(position).getRidedistance());
        holder.mDiscription.setText(dHistory.get(position).getDescription());
        holder.mBoxes.setText(dHistory.get(position).getBoxes());
        holder.mWeight.setText(dHistory.get(position).getWeight());
        holder.mPaymentStatus.setText(dHistory.get(position).getPaymentstatus());
        holder.mPaidVia.setText(dHistory.get(position).getPaidvia());
        holder.mfare.setText(dHistory.get(position).getRidefare());
        UniqueID=dHistory.get(position).getUniqueID();

        holder.mReached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mReached.setVisibility(View.GONE);
                holder.mStart.setVisibility(View.VISIBLE);
                DocumentReference docRef = db.collection("acceptRequest").document(UniqueID);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {

                                driverHistory driverHistory = new driverHistory(dHistory.get(position).getName(), dHistory.get(position).getPickup(), dHistory.get(position).getDrop(), dHistory.get(position).getPhone(), dHistory.get(position).getDate(), dHistory.get(position).getCID(), dHistory.get(position).getVT(), dHistory.get(position).getWeight(), dHistory.get(position).getBoxes(), dHistory.get(position).getDescription(), dHistory.get(position).getDriverloading(), dHistory.get(position).getRidedistance(), dHistory.get(position).getPickupaddress(), dHistory.get(position).getDropaddress(), dHistory.get(position).getDrivername(), dHistory.get(position).getDriverdp(), dHistory.get(position).getDrivernic(), dHistory.get(position).getDriverphone(), dHistory.get(position).getDriverlocation(), dHistory.get(position).getCarregno(), dHistory.get(position).getDriverid(), "Waiting", null, null, null, dHistory.get(position).getUniqueID());
                                acceptRequest acceptRequest = new acceptRequest(dHistory.get(position).getName(), dHistory.get(position).getPickup(), dHistory.get(position).getDrop(), dHistory.get(position).getPhone(), dHistory.get(position).getDate(), dHistory.get(position).getCID(), dHistory.get(position).getVT(), dHistory.get(position).getWeight(), dHistory.get(position).getBoxes(), dHistory.get(position).getDescription(), dHistory.get(position).getDriverloading(), dHistory.get(position).getRidedistance(), dHistory.get(position).getPickupaddress(), dHistory.get(position).getDropaddress(), dHistory.get(position).getDrivername(), dHistory.get(position).getDriverdp(), dHistory.get(position).getDrivernic(), dHistory.get(position).getDriverphone(), dHistory.get(position).getDriverlocation(), dHistory.get(position).getCarregno(), dHistory.get(position).getDriverid(), "Waiting", null, null, null, date, dHistory.get(position).getUniqueID());
                                db.collection("acceptRequest").document(UniqueID).set(acceptRequest);
                                db.collection("CustomerHistory").document(UniqueID).set(driverHistory);
                                db.collection("DriverHistory").document(UniqueID).set(driverHistory);

                            }
                        }
                    }


                });
               }
            });
        }

@Override
public int getItemCount() {
        return dHistory.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;
    public TextView mName,mPickup,mDrop,mPhone, mstatus, mDiscription, mBoxes, mRideDistance,mWeight, mPaymentStatus,mPaidVia, mfare;
    public Button mReached,mStart, mPayment, mFinish;

    public ViewHolder(View itemView) {
        super(itemView);
        mView= itemView;
        mName=(TextView)mView.findViewById(R.id.CRcustomername);
        mPickup=(TextView)mView.findViewById(R.id.CRpickupaddress);
        mDrop=(TextView)mView.findViewById(R.id.CRdropaddress);
        mPhone=(TextView)mView.findViewById(R.id.CRcustomerphone);
        mstatus=(TextView)mView.findViewById(R.id.CRstatus);
        mRideDistance=(TextView)mView.findViewById(R.id.CRridedistance);
        mDiscription=(TextView)mView.findViewById(R.id.CRdescription);
        mPaymentStatus=(TextView)mView.findViewById(R.id.CRpaymentstatus);
        mPaidVia=(TextView)mView.findViewById(R.id.CRpaidvia);
        mWeight=(TextView)mView.findViewById(R.id.CRweight);
        mBoxes=(TextView)mView.findViewById(R.id.CRBoxes);
        mfare=(TextView)mView.findViewById(R.id.CRfare);
        mReached=(Button)mView.findViewById(R.id.reached);
        mStart=(Button)mView.findViewById(R.id.Start);
        mPayment=(Button)mView.findViewById(R.id.takepayment);
        mFinish =(Button)mView.findViewById(R.id.finish);

    }
}
    }


