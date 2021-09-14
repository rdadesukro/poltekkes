package com.example.poltekkes.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
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
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.github.squti.guru.Guru;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.poltekkes.menu.menu_pertanyaan.jawaban;


public class adapter_pertanyaan extends RecyclerView.Adapter<adapter_pertanyaan.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    private List<DataItem_pertanyaan> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;
    public adapter_pertanyaan(Context ctx, List<DataItem_pertanyaan> mList , int animation_type, OnImageClickListener onImageClickListener) {
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
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_pertanyaan,parent, false);
            holder = new HolderData(layout);

            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final DataItem_pertanyaan dm = mList.get(position);
        holder.txt_pertanyaan.setText(dm.getText());
        holder.txt_no.setText(position+1+".");
        holder.dm = dm;
        holder.pos =position;
        setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.txt_pertanyaan)
        TextView txt_pertanyaan;

        @BindView(R.id.txt_no)
        TextView txt_no;

        @BindView(R.id.cex_jawaban)
        CheckBox cex_jawaban;

        DataItem_pertanyaan dm;

        int pos;


        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


//                   String role = Guru.getString("role", "false");
//                    if (role.equals("user")){
//
//                    }else {
//                        onImageClickListener.onImageClick(dm.getId(),
//                                dm.getNamaOptik(),
//                                dm.getAlamat(),dm.getPhone(),dm.getFoto(),dm.getLat(),dm.getLng(),dm.getStatus(),dm.getStatusBpjs(),dm.getInformasi(),dm.getJamOprasional());
//
//                    }
                       return false;
                }
            });

            cex_jawaban.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cex_jawaban.isChecked())
                       //  jawaban.set(pos,"ya");
                       onImageClickListener.onImageClick(pos,"ya"," ");
//

                    else
                      //  jawaban.set(pos,"tidak");
                        onImageClickListener.onImageClick(pos,"tidak"," ");
                     Log.i("isi_jawaban", "onClick: "+jawaban);



                }
            });

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
