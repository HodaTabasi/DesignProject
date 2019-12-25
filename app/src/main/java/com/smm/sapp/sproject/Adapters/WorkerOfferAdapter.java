package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.EditProposalFragment;
import com.smm.sapp.sproject.Fragments.UnderwayFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class WorkerOfferAdapter extends RecyclerView.Adapter<WorkerOfferAdapter.WorkerOfferHolder> {

    private Context context;
    private List<OfferModel> offerModels;
    private int num;

    public WorkerOfferAdapter(Context context, List<OfferModel> offerModels, int num) {
        this.context = context;
        this.offerModels = offerModels;
        this.num = num;
    }

    @NonNull
    @Override
    public WorkerOfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_row, parent, false);
        return new WorkerOfferHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerOfferHolder holder, int position) {
        final OfferModel offerModel = offerModels.get(position);
        holder.body.setText(offerModel.getDescr());
        holder.calender.setText(offerModel.getDur());
        if (ConstantInterFace.USER.getType().equals("client"))
            holder.name.setText(offerModel.getUser().getName());
        else
            holder.name.setText(offerModel.getProject().getUser().getName());

        holder.money.setText(offerModel.getBalance() + " ريال سعودي ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == 0 || num == 1 || num == 2) {
                    EditProposalFragment fragment = new EditProposalFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object", offerModel);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                } else if (num == 3 || num == 4) {
                    Log.e("ffrvhvs","vlhjpifspod");
                    UnderwayFragment fragment = new UnderwayFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("offer", offerModel);
                    bundle.putParcelable("user", offerModel.getUser());
                    fragment.setArguments(bundle);
                    Log.e("ffrvhvs",offerModel.getUser().getId()+" fddd" );
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    public class WorkerOfferHolder extends RecyclerView.ViewHolder {
        TextView calender, name, money, body, tv1, tv_day;

        public WorkerOfferHolder(View itemView) {
            super(itemView);

            calender = itemView.findViewById(R.id.tv_calender);
            name = itemView.findViewById(R.id.tv_name);
            money = itemView.findViewById(R.id.tv_money);
            body = itemView.findViewById(R.id.tv2);
            tv1 = itemView.findViewById(R.id.tv1);
            tv_day = itemView.findViewById(R.id.tv_day);


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            calender.setTypeface(custom_font);
            name.setTypeface(custom_font);
            money.setTypeface(custom_font);
            body.setTypeface(custom_font);
            tv1.setTypeface(custom_font);
            tv_day.setTypeface(custom_font);


        }
    }
}
