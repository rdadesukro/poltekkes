package com.example.poltekkes.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.poltekkes.R;
import com.example.poltekkes.model.detail_history.JawabanItem;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.poltekkes.menu.menu_pertanyaan.jawaban;


public class adapter_detail_history extends RecyclerView.Adapter<adapter_detail_history.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    private List<JawabanItem> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;
    public adapter_detail_history(Context ctx, List<JawabanItem> mList , int animation_type, OnImageClickListener onImageClickListener) {
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
        void lihat_gambar(int id, String gambar,ImageView foto);
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
        final JawabanItem dm = mList.get(position);
//        holder.txt_pertanyaan.setText(dm.getText());


        String test = dm.getText();
        String s=test.substring(0,1);

        if (s.equals("#"))
        {

            holder.txt_judul.setVisibility(View.VISIBLE);
            holder.radioGrup.setVisibility(View.GONE);
            holder.txt_judul.setText(dm.getText());

        }else {
            holder.radioGrup.setVisibility(View.VISIBLE);
            holder.txt_judul.setVisibility(View.GONE);
//            holder.txt_pertanyaan.setText(dm.getText());
           // holder.txt_pertanyaan.requestFocus();
            holder.txt_pertanyaan.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            holder.txt_pertanyaan.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            holder.txt_pertanyaan.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // chromium, enable hardware acceleration
                holder.txt_pertanyaan.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                // older android version, disable hardware acceleration
                holder.txt_pertanyaan.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            holder.txt_pertanyaan.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ dm.getText(), "text/html", "UTF-8", "");



        }



        if (dm.getJawaban().equals("ya")){
            holder.rd_ya.setChecked(true);
            holder.rd_tidak.setChecked(false);
            holder.rd_tidak.setSelected(false);
            holder.rd_tidak.setEnabled(false);

        }else {
            holder.rd_tidak.setChecked(true);
            holder.rd_ya.setChecked(false);
            holder.rd_ya.setSelected(false);
            holder.rd_ya.setEnabled(false);
        }

        if (dm.getGambar().equals("")){
            holder.img_gambar.setVisibility(View.GONE);
            holder.card_img.setVisibility(View.GONE);
        }else {
            holder.img_gambar.setVisibility(View.VISIBLE);
            holder.card_img.setVisibility(View.VISIBLE);
            Glide.with(ctx)
                    .load(dm.getGambar())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .error(R.drawable.error_circle)
                    .into(holder.img_gambar);
        }




        Log.i("data_pertama", "onBindViewHolder: "+s);


        holder.dm = dm;
        holder.pos =position;


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.txt_pertanyaan)
        WebView txt_pertanyaan;

        @BindView(R.id.txt_judul)
        TextView txt_judul;


        @BindView(R.id.img_gambar)
        ImageView img_gambar;

        @BindView(R.id.card_img)
        CardView card_img;




        @BindView(R.id.radio_grup)
        RadioGroup radioGrup;

        @BindView(R.id.rd_ya)
        RadioButton rd_ya;

        @BindView(R.id.rd_tidak)
        RadioButton rd_tidak;


        JawabanItem dm;

        int pos;


        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
//            v.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//
//                    onImageClickListener.lihat_gambar(pos,dm.getGambar(),img_gambar);
//                    return false;
//                }
//            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, ""+dm.getJawaban(), Toast.LENGTH_SHORT).show();

                }
            });

//            cex_jawaban.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String tidak ='"'+"tidak"+'"';
//                    String ya ='"'+"ya"+'"';
//                    if (cex_jawaban.isChecked())
//                       //  jawaban.set(pos,"ya");
//
//                       onImageClickListener.onImageClick(pos,ya," ");
////
//
//                    else
//                      //  jawaban.set(pos,"tidak");
//                        onImageClickListener.onImageClick(pos,tidak," ");
//                     Log.i("isi_jawaban", "onClick: "+jawaban);
//
//
//
//                }
//            });

//            radioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                    String tidak ='"'+"tidak"+'"';
//                    String ya ='"'+"ya"+'"';
//                    if (checkedId == R.id.rd_ya) {
//
//                        onImageClickListener.onImageClick(pos,ya," ");
//                        //Toast.makeText(ctx, "ya", Toast.LENGTH_SHORT).show();
//                    } else if (checkedId == R.id.rd_tidak) {
//
//                        onImageClickListener.onImageClick(pos,tidak," ");
//                       // Toast.makeText(ctx, "tidak", Toast.LENGTH_SHORT).show();
//                    } else {
////                        Toast.makeText(ctx, "choice: Vibration",
////                                Toast.LENGTH_SHORT).show();
//                    }
//                    Log.i("isi_jawaban", "onClick: "+jawaban);
//                }
//
//            });

//
        }


    }


}
