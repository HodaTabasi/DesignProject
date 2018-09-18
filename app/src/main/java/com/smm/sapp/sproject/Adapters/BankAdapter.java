package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.smm.sapp.sproject.Models.BankModel;
import com.smm.sapp.sproject.Models.UserModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankHolder> {

    private Context context;
    private List<UserModel.BanksBean> bankList;

    public BankAdapter(Context context, List<UserModel.BanksBean> bankList) {
        this.context = context;
        this.bankList = bankList;
    }

    @NonNull
    @Override
    public BankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_row, parent, false);
        return new BankHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankHolder holder, int position) {
        holder.et_bankName.setText(bankList.get(position).getName());
        holder.et_bankNum.setText(bankList.get(position).getNumber());
        holder.et_kNum.setText(bankList.get(position).getIban());

    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    public class BankHolder extends RecyclerView.ViewHolder {

        EditText et_bankName;
        EditText et_bankNum;
        EditText et_kNum;

        public BankHolder(View itemView) {
            super(itemView);

            et_bankName = itemView.findViewById(R.id.et_bankName);
            et_bankNum = itemView.findViewById(R.id.et_bankNum);
            et_kNum = itemView.findViewById(R.id.et_kNum);
        }
    }
}
