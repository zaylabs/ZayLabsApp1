package com.zaylabs.zaylabsapp1.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.DTO.driverProfile;
import com.zaylabs.zaylabsapp1.R;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.Customadapternew;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobListFragment extends android.app.Fragment {

    private RecyclerView mJoblist;

    private ArrayList<customerRequest> list;
    ListView listView;
    Context context;

    private FirebaseFirestore db;

    public JobListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);

        listView=view.findViewById(R.id.listview);

        db = FirebaseFirestore.getInstance();



        db.collection("vt1customerRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                customerRequest request = document.toObject(customerRequest.class);
                                String name = request.getName();
                                String phone = request.getPhone();
                                GeoPoint pickup= request.getPickup();
                                GeoPoint drop = request.getDrop();
                                list.add(new customerRequest(name,pickup,drop,phone));
                            }
                        } else {

                        }
                    }
                });

        Customadapternew customadapternew = new Customadapternew(list,context);
        listView.setAdapter(customadapternew);


        return view;
    }

}
