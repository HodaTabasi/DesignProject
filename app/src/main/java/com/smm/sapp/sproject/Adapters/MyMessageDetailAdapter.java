package com.smm.sapp.sproject.Adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
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
        MessageDetails message = details.get(position);

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
        MessageDetails message = details.get(position);

        if (message.getSender_id().equals(String.valueOf(ConstantInterFace.USER.getId()))) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView  mAttachs;
        ImageView mimageView;
        TextView mBody;

        public SentMessageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_message_profile_s);
            mBody = itemView.findViewById(R.id.text_message_body_s);
            mAttachs = itemView.findViewById(R.id.text_message_attachs_s);
            mimageView = itemView.findViewById(R.id.image_message_s);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            mBody.setTypeface(custom_font);
            mAttachs.setTypeface(custom_font);
        }

        void bind(final MessageDetails message) {
            Picasso.get().load(message.getUser().getPhoto_link()).into(imageView);
            mBody.setText(message.getMessage());
            if (message.getFile_link() == null){
                mAttachs.setVisibility(View.GONE);
                mimageView.setVisibility(View.GONE);
            }else {
                Boolean aBoolean = ifAnImage(message.getFile_link());
                if (aBoolean){
                    mAttachs.setVisibility(View.GONE);
                    mimageView.setVisibility(View.VISIBLE);
                    if (!(message.getWidth().equals("0")&& message.getHeight().equals("0"))){
                        double w =Double.parseDouble(message.getWidth());
                        double h =Double.parseDouble(message.getHeight());
                        mimageView.getLayoutParams().height = (int) ((w / h) * 350);
                        mimageView.requestLayout();
                    }
                    Picasso.get().load(message.getFile_link()).into(mimageView);
                }else {
                    mAttachs.setVisibility(View.VISIBLE);
                    mimageView.setVisibility(View.GONE);
                    mAttachs.setText(URLUtil.guessFileName(message.getFile_link(), null, null));
                    mAttachs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dounloadAttachment(message.getFile_link(),1);
                        }
                    });
                }

            }
        }
    }



    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView mName, mAttachs;
        ImageView mimageView;
        TextView  mBody;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_message_profile);
            mBody = itemView.findViewById(R.id.text_message_body);
            mAttachs = itemView.findViewById(R.id.text_message_attachs);
            mimageView = itemView.findViewById(R.id.image_message);
            //mName = itemView.findViewById(R.id.text_message_name);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            mBody.setTypeface(custom_font);
            mAttachs.setTypeface(custom_font);
            //mName.setTypeface(custom_font);
        }

        void bind(final MessageDetails message) {
            Picasso.get().load(message.getUser().getPhoto_link()).into(imageView);
            mBody.setText(message.getMessage());
            //mName.setText(message.getUser().getName());

            if (message.getFile_link() == null){
                mAttachs.setVisibility(View.GONE);
                mimageView.setVisibility(View.GONE);
            }else {
                Boolean aBoolean = ifAnImage(message.getFile_link());
                if (aBoolean){
                    mAttachs.setVisibility(View.GONE);
                    mimageView.setVisibility(View.VISIBLE);
                    if (!(message.getWidth().equals("0")&& message.getHeight().equals("0"))){
                        double w =Double.parseDouble(message.getWidth());
                        double h =Double.parseDouble(message.getHeight());
                        mimageView.getLayoutParams().height = (int) ((w / h) * 350);
                        mimageView.requestLayout();
                    }
                    Picasso.get().load(message.getFile_link()).into(mimageView);
                }else {
                    mAttachs.setVisibility(View.VISIBLE);
                    mimageView.setVisibility(View.GONE);
                    mAttachs.setText(URLUtil.guessFileName(message.getFile_link(), null, null));

                    mAttachs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dounloadAttachment(message.getFile_link(),1);
                        }
                    });
                }
            }

        }
    }

    private Boolean ifAnImage(final String file_link) {
        String word = ".";

//        System.out.println(file_link.indexOf(word)); // prints "4"
//        System.out.println(file_link.lastIndexOf(word)); // prints "22"

        String s = file_link.substring(file_link.lastIndexOf(word));

        if (s.equals(".jpg") || s.equals(".jpeg") || s.equals(".png") || s.equals(".gif")){
            return true;
        }else {
            return false;
        }
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                while(true) {
//                    URLConnection connection = null;
//                    try {
//                        connection = new URL(file_link).openConnection();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String contentType = connection.getHeaderField("Content-Type");
//                     image = contentType.startsWith("image/");
//                }
//            }
//        };
//
//        thread.start();
    }

    private void dounloadAttachment(String file_link, int ids) {
        Uri downloadUri = Uri.parse(file_link);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        long id = downloadManager.enqueue(request);
    }
}
