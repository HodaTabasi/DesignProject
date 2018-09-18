package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.smm.sapp.sproject.Models.FeedbackModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackHolder> {

    private Context context;
    private List<FeedbackModel> feedbackList;

    public FeedbackAdapter(Context context, List<FeedbackModel> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_row, parent, false);
        return new FeedbackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class FeedbackHolder extends RecyclerView.ViewHolder {

        EditText et_comment;

        public FeedbackHolder(View itemView) {
            super(itemView);

            et_comment = itemView.findViewById(R.id.et_comment);

        }
    }
}
