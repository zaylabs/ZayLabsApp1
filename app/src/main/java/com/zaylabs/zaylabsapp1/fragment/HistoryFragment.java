package com.zaylabs.zaylabsapp1.fragment;


import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.MainActivity;
import com.zaylabs.zaylabsapp1.R;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.EventsRecyclerViewAdapter;
import com.zaylabs.zaylabsapp1.RecycleViewAdapters.UsersAdapter;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    private FirebaseFirestore firestoreDB;
    private ListView mListview;

    private String mName, mPhone;
    private GeoPoint mPickup,mDrop;
    private ArrayList<customerRequest> arrayOfUsers = new ArrayList<customerRequest>();
    private Button mRefresh;
    private customerRequest request;
    private customerRequest data;
    private UsersAdapter adapter;

    public HistoryFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        firestoreDB = FirebaseFirestore.getInstance();
        mListview = view.findViewById(R.id.mListView);
        mRefresh = view.findViewById(R.id.refresh);

        adapter = new UsersAdapter(getActivity(), arrayOfUsers);
        mListview.setAdapter(adapter);
        data = new customerRequest(mName,mPickup,mDrop,mPhone);
        arrayOfUsers.add(data);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListItems();
            }
        });
        return view;


    }
    public void getListItems() {
        firestoreDB.collection("vt1customerRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                request = (customerRequest) document.getData();
                                mName=request.getName();
                                mPickup=request.getPickup();
                                mDrop=request.getDrop();
                                mPhone=request.getPhone();
                                data = new customerRequest(mName,mPickup,mDrop,mPhone);
                                arrayOfUsers.add(data);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



    }








}



