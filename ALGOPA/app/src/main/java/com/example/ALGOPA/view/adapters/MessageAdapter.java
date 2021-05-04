package com.example.ALGOPA.view.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ALGOPA.R;
import com.example.ALGOPA.services.model.Chats;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private static final int MSG_TYPE_LEFT_RECEIVED = 0;
    private static final int MSG_TYPE_RIGHT_RECEIVED = 1;
    private ArrayList<Chats> chatArrayList;
    private Context context;
    private String currentUser_sender;

    public MessageAdapter(ArrayList<Chats> chatArrayList, Context context, String currentUser_sender) {
        this.chatArrayList = chatArrayList;
        this.context = context;
        this.currentUser_sender = currentUser_sender;
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT_RECEIVED) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right_sent, parent, false);
            return new MessageHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left_received, parent, false);
            return new MessageHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Chats chats = chatArrayList.get(position);
        String message = chats.getMessage();
        String timeStamp = chats.getTimestamp();
        boolean isSeen = chats.getSeen();
        long intTimeStamp = Long.parseLong(timeStamp);
        String time_msg_received = timeStampConversionToTime(intTimeStamp);
        holder.tv_time.setText(time_msg_received);
        holder.tv_msg.setText(message);

        //click to show delete dialog
        MessageHolder.messageLAyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click to show delete dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this message?");
                //delete button
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteMessage(position);
                    }
                });
                //cancel delete button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                //create and show dialog
                builder.create().show();
            }
        });

        if (position == chatArrayList.size() - 1) {
            if (isSeen) {
                holder.tv_seen.setVisibility(View.VISIBLE);
                String seen = "Seen";
                holder.tv_seen.setText(seen);
            } else {
                holder.tv_seen.setVisibility(View.VISIBLE);
                String delivered = "Delivered";
                holder.tv_seen.setText(delivered);
            }
        } else {
            holder.tv_seen.setVisibility(View.GONE);
        }
    }

    private void deleteMessage(int position) {
        String myUID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        /* Logic:
         * Get timestamp of clicked message
         * Compare the timestamp of the clicked message with all messages in Chats
         * Where both values matches delete that message
         * This will allow sender to delete his and receiver's message*/
        String msgTimeStamp = chatArrayList.get(position).getTimestamp();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Chats");
        Query query = dbRef.orderByChild("timestamp").equalTo(msgTimeStamp);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    /*if you want to allow sender to delete only his message then
                     compare sender value with current user's uid
                     if they match means its the message of sender that is trying to delete*/
                    if (myUID.equals(ds.child("senderId").getValue())){
                        /*We can do one of two things here
                         * 1) Remove the message from Chats
                         * 2) Set the Value of message "This message was deleted..."
                         * So do whatever you want"*/

                        //1) Remove the message from Chats"
                        ds.getRef().removeValue(); //now test this

                        //2) Set the Value of message "This message was deleted..."//test this
                        /*HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("message", "This message was deleted...");
                        ds.getRef().updateChildren(hashMap);*/

                        Toast.makeText(context, "message deleted...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "You can delete only your messages...", Toast.LENGTH_SHORT).show();
                    }




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String timeStampConversionToTime(long timeStamp) {

        Date date = new Date(timeStamp);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat jdf = new SimpleDateFormat("hh:mm a");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return jdf.format(date);

    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }



    public static class MessageHolder extends RecyclerView.ViewHolder {
        TextView tv_msg;
        TextView tv_time;
        TextView tv_seen;
        static RelativeLayout messageLAyout; //for click listener to show delete


        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            tv_msg = itemView.findViewById(R.id.tv_chat_received);
            tv_time = itemView.findViewById(R.id.tv_chat_time_received);
            tv_seen = itemView.findViewById(R.id.tv_seen);
            messageLAyout = itemView.findViewById(R.id.messageLayout);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatArrayList.get(position).getReceiverId().equals(currentUser_sender)) {
            return MSG_TYPE_LEFT_RECEIVED;
        } else return MSG_TYPE_RIGHT_RECEIVED;
    }
}
