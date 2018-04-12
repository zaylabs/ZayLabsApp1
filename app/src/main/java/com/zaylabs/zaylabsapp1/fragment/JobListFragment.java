package com.zaylabs.zaylabsapp1.fragment;


import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.customerRequestAdapter;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobListFragment extends Fragment {


    private FirebaseFirestore firestoreDB;
    private RecyclerView mListview;

    private String mName, mPhone;
    private GeoPoint mPickup, mDrop;
    private List<customerRequest> cRequests;
    private List<customerRequest> cDriverInfo;
    private Button mRefresh;
    private customerRequest request;
    private customerRequest data;
    private customerRequestAdapter customerRequestAdapter;
    private Location mCurrentLocation;

    public JobListFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joblist, container, false);
        firestoreDB = FirebaseFirestore.getInstance();



        cRequests = new ArrayList<>();
        customerRequestAdapter = new customerRequestAdapter(getActivity(),cRequests);
        mListview = (RecyclerView)view.findViewById(R.id.mListView);
        mListview.setHasFixedSize(true);
        mListview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListview.setAdapter(customerRequestAdapter);


        firestoreDB.collection("customerRequest").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error:" + e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED){
                        String user_id = doc.getDocument().getId();
                        customerRequest request = doc.getDocument().toObject(customerRequest.class).withID(user_id);

                        cRequests.add(request);
                        customerRequestAdapter.notifyDataSetChanged();

                    }

                }
            }
        });


        return view;
    }
}