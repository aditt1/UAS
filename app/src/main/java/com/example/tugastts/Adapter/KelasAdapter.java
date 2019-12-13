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

import com.example.tugastts.Model.Kelas;
import com.example.tugastts.Module.MainActivity;
import com.example.tugastts.R;

import java.util.ArrayList;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {
    ArrayList<Kelas> klsArrayList;

    public KelasAdapter(ArrayList<Kelas> klsArrayList) {
        this.klsArrayList = klsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_kelas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtHari.setText(klsArrayList.get(position).getHari());
        holder.txtJumMhs.setText(klsArrayList.get(position).getJumMhs());
        holder.txtDsn.setText(klsArrayList.get(position).getDosen());
        holder.txtSesi.setText(klsArrayList.get(position).getSesi());
//        holder.ImgDsn.setImageResource(klsArrayList.get(position).getImgDsn());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (klsArrayList != null) ?klsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHari, txtJumMhs, txtDsn, txtSesi;
//        ImageView ImgDsn;

        public ViewHolder(View view){
            super(view);

            txtHari = view.findViewById(R.id.txtHariKelas);
            txtJumMhs = view.findViewById(R.id.txtJumlahMhs);
            txtDsn = view.findViewById(R.id.txtDosenKelas);
            txtSesi = view.findViewById(R.id.txtSesiKelas);
//            ImgDsn = view.findViewById(R.id.ImgDsn);
        }
    }
}
