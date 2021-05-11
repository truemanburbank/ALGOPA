package com.example.ALGOPA.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.request.transition.ViewTransition;
import com.example.ALGOPA.R;
import com.example.ALGOPA.services.model.ModelPost;
import com.example.ALGOPA.services.model.Users;
import com.example.ALGOPA.view.adapters.AdapterPost;
import com.example.ALGOPA.view.adapters.UserFragmentAdapter;
import com.example.ALGOPA.view.ui.AddPostActivity;
import com.example.ALGOPA.viewModel.DatabaseViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MentoringFragment extends Fragment {

    private Context context;

    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    List<ModelPost> postList;
    AdapterPost adapterPost;
    AppCompatEditText et_search;

    public MentoringFragment(Context context) {
        this.context = context;
    }


    public MentoringFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mentoring, container, false);


        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //recycler view and its properties
        recyclerView = view.findViewById(R.id.postRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //show newest post first, for this load from last

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        //set layout yo recyclerview
        recyclerView.setLayoutManager(layoutManager);

        //init post list
        postList = new ArrayList<>();

        loadPosts();
        EditText et_search = view.findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                searchPosts(s.toString().toLowerCase());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_search.getText().toString().startsWith(" "))
                    et_search.setText("");
            }
        });

        return view;
    }



    private void loadPosts() {
        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);

                    postList.add(modelPost);

                    //adapter
                    adapterPost = new AdapterPost(getActivity(), postList);
                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterPost);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(context, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchPosts(String searchQuery){
        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);

                    if (modelPost.getpTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            modelPost.getpDescr().toLowerCase().contains(searchQuery.toLowerCase())){
                        postList.add(modelPost);
                    }

                    //adapter
                    adapterPost = new AdapterPost(getActivity(), postList);
                    //set adapter to recyclerview
                    recyclerView.setAdapter(adapterPost);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(context, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}