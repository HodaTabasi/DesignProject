package com.example.maryam.sproject.Adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Models.Attachments;
import com.example.maryam.sproject.R;

import java.util.List;

public class ProjectAttachmentAdapter extends RecyclerView.Adapter<ProjectAttachmentAdapter.ProjectAttachmentVH> {
    Context context;
    int layout;
    List<Attachments> attachmentsList;

    public ProjectAttachmentAdapter(Context context, int layout, List<Attachments> attachmentsList) {
        this.context = context;
        this.layout = layout;
        this.attachmentsList = attachmentsList;
    }

    @NonNull
    @Override
    public ProjectAttachmentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ProjectAttachmentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAttachmentVH holder, final int position) {
        holder.attachments.setText(attachmentsList.get(position).getId() +" ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dounloadAttachment(attachmentsList.get(position).getFile_link(),attachmentsList.get(position).getId());
            }
        });
    }

    private void dounloadAttachment(String file_link, int ids) {
        Uri downloadUri = Uri.parse(file_link);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        long id =  downloadManager.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return attachmentsList.size();
    }

    public class ProjectAttachmentVH extends RecyclerView.ViewHolder{
        TextView attachments;
        public ProjectAttachmentVH(View itemView) {
            super(itemView);
            attachments = itemView.findViewById(R.id.attachments);
        }
    }
}
