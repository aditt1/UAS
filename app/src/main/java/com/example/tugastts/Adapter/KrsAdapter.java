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

import com.example.tugastts.Model.Krs;
import com.example.tugastts.Module.CreateKRSActivity;
import com.example.tugastts.R;

import java.util.ArrayList;

public class KrsAdapter extends RecyclerView.Adapter<KrsAdapter.ViewHolder> {
    private ArrayList<Krs> dataList;
    private Context context;
    public KrsAdapter (ArrayList<Krs> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_krs,parent,false); //
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //gunanya utk memasukkan data
        holder.txtKodeMk.setText(dataList.get(position).getKodeMkKrs());
        holder.txtNamaMk.setText(dataList.get(position).getNamaMkKrs());
        holder.txtHari.setText(dataList.get(position).getHariKrs());
        holder.txtSesi.setText(dataList.get(position).getSesiKrs());
        holder.txtJmlSks.setText(dataList.get(position).getSksKrs());
        holder.txtJmlMhs.setText(dataList.get(position).getJmlMhs());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context != null){
                    Intent intent = new Intent(context, CreateKRSActivity.class);
                    context.startActivity(intent);}
            }
        });
    }

    @Override
    public int getItemCount() { //berguna untuk menghitung jumlah data yang ada
        return (dataList != null)? dataList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{ //utk menghubungkan dari txt
        private TextView txtKodeMk, txtNamaMk, txtHari, txtSesi, txtJmlSks, txtJmlMhs;
        private CardView cv;

        public ViewHolder(View view){
            super(view);
            txtKodeMk = view.findViewById(R.id.txtKodematkul);
            txtNamaMk = view.findViewById(R.id.txtNamamatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJmlSks = view.findViewById(R.id.txtSks);
            txtJmlMhs = view.findViewById(R.id.txtJumlah);
            cv = view.findViewById(R.id.cvKrs);
        }
    }
}
