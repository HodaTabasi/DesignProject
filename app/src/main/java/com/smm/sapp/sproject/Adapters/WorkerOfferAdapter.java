package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.EditProposalFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class WorkerOfferAdapter extends RecyclerView.Adapter<WorkerOfferAdapter.WorkerOfferHolder> {

    private Context context;
    private List<OfferModel> offerModels;

    public WorkerOfferAdapter(Context context, List<OfferModel> offerModels) {
        this.context = context;
        this.offerModels = offerModels;
    }

    @NonNull
    @Override
    public WorkerOfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_row, parent, false);
        return new WorkerOfferHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerOfferHolder holder, int position) {
        final OfferModel projectsModels = offerModels.get(position);
        holder.body.setText(projectsModels.getDescr());
        holder.calender.setText(projectsModels.getDur());
        holder.name.setText(projectsModels.getId() + " ");
        holder.money.setText(projectsModels.getBalance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProposalFragment fragment = new EditProposalFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("object",projectsModels);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context,R.id.container_activity,fragment,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    public class WorkerOfferHolder extends RecyclerView.ViewHolder {
        TextView calender, name, money, body;

        public WorkerOfferHolder(View itemView) {
            super(itemView);

            calender = itemView.findViewById(R.id.tv_calender);
            name = itemView.findViewById(R.id.tv_name);
            money = itemView.findViewById(R.id.tv_money);
            body = itemView.findViewById(R.id.tv2);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            calender.setTypeface(custom_font);
            name.setTypeface(custom_font);
            money.setTypeface(custom_font);
            body.setTypeface(custom_font);
        }
    }
}
