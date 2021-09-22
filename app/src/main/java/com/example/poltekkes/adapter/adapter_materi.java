package com.example.poltekkes.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.poltekkes.R;
import com.example.poltekkes.model.materi.DataItem_materi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_materi extends RecyclerView.Adapter<adapter_materi.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    private List<DataItem_materi> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;
    public adapter_materi(Context ctx, List<DataItem_materi> mList , int animation_type, OnImageClickListener onImageClickListener) {
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
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.materi,parent, false);
            holder = new HolderData(layout);

            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final DataItem_materi dm = mList.get(position);


        Glide.with(ctx)
                .load(dm.getUrl())
                .listener(new RequestListener<Drawable>() {


                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                      //  holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                     //   holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.pos =position;
        setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;


        DataItem_materi dm;

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
