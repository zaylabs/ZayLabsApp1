package com.zaylabs.zaylabsapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaylabs.zaylabsapp1.DTO.acceptRequest;
import com.zaylabs.zaylabsapp1.DTO.driverHistory;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.currentRideAdapter;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.driverHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class current_Ride extends AppCompatActivity {

    private FirebaseFirestore firestoreDB;
    private RecyclerView mDhistory;

    private List<acceptRequest> dHistory;
    private acceptRequest acceptRequest;
    private currentRideAdapter currentRideAdapter;
    private String driverID;
    private String TAG;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current__ride);
        firestoreDB = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        driverID = mAuth.getCurrentUser().getUid();
        dHistory = new ArrayList<>();

        currentRideAdapter = new currentRideAdapter(this,dHistory);
        mDhistory = (RecyclerView)findViewById(R.id.currentRideRV);
        mDhistory.setHasFixedSize(true);
        mDhistory.setLayoutManager(new LinearLayoutManager(this));
        mDhistory.setAdapter(currentRideAdapter);


        firestoreDB.collection("acceptRequest").whereEqualTo("driverid", driverID).addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error:" + e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    switch (doc.getType()) {
                        case ADDED:
                            acceptRequest = doc.getDocument().toObject(acceptRequest.class);
                            dHistory.add(acceptRequest);
                            currentRideAdapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            acceptRequest = doc.getDocument().toObject(acceptRequest.class);
                            dHistory.add(acceptRequest);
                            currentRideAdapter.notifyDataSetChanged();
                            break;
                        case REMOVED:
                            dHistory.remove(acceptRequest);
                            currentRideAdapter.notifyDataSetChanged();
                            break;
                    }

                }
            }
        });

    }
}
