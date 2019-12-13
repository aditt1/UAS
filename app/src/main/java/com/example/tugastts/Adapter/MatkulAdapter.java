package com.example.tugastts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugastts.Model.Matkul;
import com.example.tugastts.Module.CreateMatkulActivity;
import com.example.tugastts.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder> {
    private ArrayList<Matkul> dataList;
    private Context context;
    public MatkulAdapter (ArrayList<Matkul> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_matkul,parent,false); //
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //gunanya utk memasukkan data
        holder.txtKodeMk.setText(dataList.get(position).getKodeMk());
        holder.txtNamaMk.setText(dataList.get(position).getNamaMk());
        holder.txtHari.setText(dataList.get(position).getHari());
        holder.txtSesi.setText(dataList.get(position).getSesi());
        holder.txtJmlSks.setText(dataList.get(position).getSks());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context != null){
                    Intent intent = new Intent(context, CreateMatkulActivity.class);
                    context.startActivity(intent);}
            }
        });
    }

    @Override
    public int getItemCount() { //berguna untuk menghitung jumlah data yang ada
        return (dataList != null)? dataList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{ //utk menghubungkan dari txt
        private TextView txtKodeMk, txtNamaMk, txtHari, txtSesi, txtJmlSks;
        private CardView cv;

        public ViewHolder(View view){
            super(view);
            txtKodeMk = view.findViewById(R.id.txtKodeKelas);
            txtNamaMk = view.findViewById(R.id.txtNamaMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJmlSks = view.findViewById(R.id.txtSks);
            cv = view.findViewById(R.id.cvMatkul);
        }
    }
}
