package com.example.poltekkes.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.poltekkes.R;
import com.example.poltekkes.menu.menu_detail_history;
import com.example.poltekkes.menu.menu_login;
import com.example.poltekkes.menu.menu_register;
import com.example.poltekkes.model.history.DataItem_history;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.github.squti.guru.Guru;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maes.tech.intentanim.CustomIntent;

import static com.example.poltekkes.menu.menu_pertanyaan.jawaban;


public class adapter_history extends RecyclerView.Adapter<adapter_history.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    private List<DataItem_history> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;
    public adapter_history(Context ctx, List<DataItem_history> mList , int animation_type, OnImageClickListener onImageClickListener) {
        this.jenis = jenis;
        this.animation_type = animation_type;
        this.mList = mList;
        this.ctx = ctx;
        this.onImageClickListener = onImageClickListener;

    }
    public interface OnImageClickListener {
        void onImageClick(int id,
                          String nama,
                          String alamat);
    }



    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout;
        HolderData holder;
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_anak,parent, false);
            holder = new HolderData(layout);

            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final DataItem_history dm = mList.get(position);
        holder.txt_nama.setText(dm.getNamaBalita());
        holder.txt_tgl_lahir.setText(dm.getTanggalLahir());
        holder.tgl_periksa.setText(dm.getTanggalPemeriksaan());
        holder.dm = dm;
        holder.pos =position;
        setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.txt_nama)
        TextView txt_nama;

        @BindView(R.id.txt_tgl_lahir)
        TextView txt_tgl_lahir;

        @BindView(R.id.tgl_periksa)
        TextView tgl_periksa;

//

        DataItem_history dm;

        int pos;


        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            tgl_periksa.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(ctx, ""+dm.getNamaBalita(), Toast.LENGTH_SHORT).show();
                    Intent goInput = new Intent(ctx, menu_detail_history.class);
                    ctx.startActivity(goInput);
                    Guru.putString("id_detail_histiry", String.valueOf(dm.getId()));
                    CustomIntent.customType(ctx, "fadein-to-fadeout");
                    return false;
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, ""+dm.getNamaBalita(), Toast.LENGTH_SHORT).show();
                    Intent goInput = new Intent(ctx, menu_detail_history.class);
                    ctx.startActivity(goInput);
                    Guru.putString("id_detail_histiry", String.valueOf(dm.getId()));
                    CustomIntent.customType(ctx, "fadein-to-fadeout");
                }
            });
//
//            cex_jawaban.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (cex_jawaban.isChecked())
//                       //  jawaban.set(pos,"ya");
//                       onImageClickListener.onImageClick(pos,"ya"," ");
////
//
//                    else
//                      //  jawaban.set(pos,"tidak");
//                        onImageClickListener.onImageClick(pos,"tidak"," ");
//                     Log.i("isi_jawaban", "onClick: "+jawaban);
//
//
//
//                }
//            });

//
        }


    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
           // ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}
