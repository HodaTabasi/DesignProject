package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.AskUs;
import com.smm.sapp.sproject.R;

import java.util.List;

public class AskUsAdapter extends RecyclerView.Adapter<AskUsAdapter.AskUsVH> {

    Context context;
    int layout;
    List<AskUs> askUses;

    public AskUsAdapter(Context context, int layout, List<AskUs> askUses) {
        this.context = context;
        this.layout = layout;
        this.askUses = askUses;
    }

    @NonNull
    @Override
    public AskUsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new AskUsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AskUsVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return askUses.size();
    }

    public class AskUsVH extends RecyclerView.ViewHolder{
        TextView title ,answer;
        public AskUsVH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title1);
            answer = itemView.findViewById(R.id.description);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            title.setTypeface(custom_font);
            answer.setTypeface(custom_font);

        }
    }
}
