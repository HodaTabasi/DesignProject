package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.Comments;
import com.smm.sapp.sproject.Models.FeedbackModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackHolder> {

    private Context context;
    private List<Comments> feedbackList;

    public FeedbackAdapter(Context context, List<Comments> feedbackList) {
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
        holder.et_comment.setText(feedbackList.get(position).getComment());
        holder.rate1.setRating(Float.valueOf(feedbackList.get(position).getRate()));
        holder.rate2.setRating(Float.valueOf(feedbackList.get(position).getRate2()));
        holder.rate3.setRating(Float.valueOf(feedbackList.get(position).getRate3()));
        holder.rate4.setRating(Float.valueOf(feedbackList.get(position).getRate4()));
        holder.rate5.setRating(Float.valueOf(feedbackList.get(position).getRate5()));
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class FeedbackHolder extends RecyclerView.ViewHolder {

        TextView et_comment;
        RatingBar rate1,rate2,rate3,rate4 ,rate5;

        public FeedbackHolder(View itemView) {
            super(itemView);

            et_comment = itemView.findViewById(R.id.et_comment);
            rate1 = itemView.findViewById(R.id.rate1);
            rate2 = itemView.findViewById(R.id.rate2);
            rate3 = itemView.findViewById(R.id.rate3);
            rate4 = itemView.findViewById(R.id.rate4);
            rate5 = itemView.findViewById(R.id.rate5);
        }
    }
}
