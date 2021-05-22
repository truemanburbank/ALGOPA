package com.example.ALGOPA.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ALGOPA.R;
import com.example.ALGOPA.services.model.Users;
import com.example.ALGOPA.view.adapters.AdapterParticipantAdd;
import com.example.ALGOPA.view.fragments.UserFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class GroupInfoActivity extends AppCompatActivity {

    private String groupId;
    private String myGroupRole = "";

    private FirebaseAuth firebaseAuth;

    private ActionBar actionBar;

    //ui views
    private ImageView groupIconIv;
    private TextView descriptionTv, createdByTv, editGroupTv, addParticipantTv, leaveGroupTv, participantsTv;
    private RecyclerView participantsRv;

    private ArrayList<Users> userList;
    private AdapterParticipantAdd adapterParticipantAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        groupIconIv = findViewById(R.id.groupIconIv);
        descriptionTv= findViewById(R.id.descriptionTv);
        createdByTv = findViewById(R.id.createdByTv);
        editGroupTv = findViewById(R.id.editGroupTv);
        addParticipantTv = findViewById(R.id.addParticipantTv);
        leaveGroupTv = findViewById(R.id.leaveGroupTv);
        participantsTv = findViewById(R.id.participantsTv);
        participantsRv = findViewById(R.id.participantsRv);

        groupId = getIntent().getStringExtra("groupId");
        
        firebaseAuth = FirebaseAuth.getInstance();
        loadGroupInfo();
        loadGroupRole();

        addParticipantTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupInfoActivity.this, GroupParticipantAddActivity.class);
                intent.putExtra("groupId", groupId);
                startActivity(intent);
            }
        });

        editGroupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupInfoActivity.this, GroupEditActivity.class);
                intent.putExtra("groupId", groupId);
                startActivity(intent);
            }
        });

        leaveGroupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if user is participant/admin: leave group
                //if user is creator: delete group
                String dialogTitle="";
                String dialogDescription="";
                String positiveButtonTitle = "";
                if(myGroupRole.equals("creator")) {
                    dialogTitle="Delete Group";
                    dialogDescription = "Are you sure you want to Delete group permanently?";
                    positiveButtonTitle = "Delete";
                }
                else {
                    dialogTitle = "Leave Group";
                    dialogDescription = "Are you sure you want to Leave group permanently?";
                    positiveButtonTitle = "LEAVE";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupInfoActivity.this);
                builder.setTitle(dialogTitle)
                        .setMessage(dialogDescription)
                        .setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (myGroupRole.equals("creator")) {
                                    //im creator of group: delete group
                                    deleteGroup();
                                }
                                else {
                                    //im participant/admin: leave group
                                    leaveGroup();
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void leaveGroup() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.child(groupId).child("Participants").child(firebaseAuth.getUid())
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //group left successfully
                        Toast.makeText(GroupInfoActivity.this, "Group left successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GroupInfoActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed to leave group
                        Toast.makeText(GroupInfoActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteGroup() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.child(groupId)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //group deleted successfully
                        Toast.makeText(GroupInfoActivity.this, "Group successfully deleted!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GroupInfoActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed to delete group
                        Toast.makeText(GroupInfoActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadGroupInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.orderByChild("groupId").equalTo(groupId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    //get group info
                    String groupId = "" + ds.child("groupId").getValue();
                    String groupTitle = "" + ds.child("groupTitle").getValue();
                    String groupDescription = "" + ds.child("groupDescription").getValue();
                    String groupIcon = "" + ds.child("groupIcon").getValue();
                    String createdBy = "" + ds.child("createdBy").getValue();
                    String timestamp = "" + ds.child("timestamp").getValue();

                    //convert time stamp to dd/mm/yyyy hh:mm am/pm
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);

                    try {
                        cal.setTimeInMillis(Long.parseLong(timestamp));
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }

                    String dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa", cal).toString();

                    loadCreatorInfo(dateTime, createdBy);

                    //set group info
                    actionBar.setTitle(groupTitle);
                    descriptionTv.setText(groupDescription);

                    try {
                        Picasso.get().load(groupIcon).placeholder(R.drawable.ic_group).into(groupIconIv);
                    }
                    catch (Exception e) {
                        groupIconIv.setImageResource(R.drawable.img_no);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCreatorInfo(String dateTime, String createdBy) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    String name = "" + ds.child("username").getValue();
                    createdByTv.setText("Created by " + name +" on " + dateTime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadGroupRole() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.child(groupId).child("Participants")
                .orderByChild("uid").equalTo(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren()) {
                            myGroupRole = "" +ds.child("role").getValue();
                            actionBar.setSubtitle(firebaseAuth.getCurrentUser().getEmail() + " ("+ myGroupRole+")");

                            if (myGroupRole.equals("participant")) {
                                editGroupTv.setVisibility(View.GONE);
                                addParticipantTv.setVisibility(View.GONE);
                                leaveGroupTv.setText("Leave Group");
                            }
                            else if (myGroupRole.equals("admin")) {
                                editGroupTv.setVisibility(View.GONE);
                                addParticipantTv.setVisibility(View.VISIBLE);
                                leaveGroupTv.setText("Leave Group");
                            }else if (myGroupRole.equals("creator")) {
                                editGroupTv.setVisibility(View.VISIBLE);
                                addParticipantTv.setVisibility(View.VISIBLE);
                                leaveGroupTv.setText("Delete Group");
                            }
                        }

                        loadParticipants();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void loadParticipants() {
        userList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.child(groupId).child("Participants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              userList.clear();
              for (DataSnapshot ds: snapshot.getChildren()) {
                  //get uid from Group > Participants
                  String uid = "" + ds.child("uid").getValue();

                  //get info og user using uid we got above
                  DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                  ref.orderByChild("id").equalTo(uid).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          for (DataSnapshot ds: snapshot.getChildren()) {
                              Users users = ds.getValue(Users.class);

                              userList.add(users);
                          }
                          //adapter
                          adapterParticipantAdd = new AdapterParticipantAdd(GroupInfoActivity.this, userList, groupId, myGroupRole);
                          //set
                          participantsRv.setAdapter(adapterParticipantAdd);
                          participantsTv.setText("Participants (" + userList.size()+")");
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}