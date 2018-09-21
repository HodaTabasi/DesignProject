package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.MessageDetails;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyMessageDetailAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    Context context;
    List<MessageDetails> details;

    public MyMessageDetailAdapter(Context context, List<MessageDetails> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_sender, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageDetails message =  details.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        MessageDetails message =  details.get(position);

        if (message.getSender_id().equals("2")) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
            TextView mBody;
        public SentMessageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_message_profile_s);
            mBody = itemView.findViewById(R.id.text_message_body_s);
        }

        void bind(MessageDetails message) {
            Picasso.get().load(message.getUser().getPhoto_link()).into(imageView);
            mBody.setText(message.getMessage());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView  mBody, mName;
        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_message_profile);
            mBody = itemView.findViewById(R.id.text_message_body);
            mName = itemView.findViewById(R.id.text_message_name);
        }

        void bind(MessageDetails message) {
            Picasso.get().load(message.getUser().getPhoto_link()).into(imageView);
            mBody.setText(message.getMessage());
            mName.setText(message.getUser().getName());

        }
    }
}
