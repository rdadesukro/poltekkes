package com.example.poltekkes.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.poltekkes.R;
import com.example.poltekkes.model.slider.DataItem_slider;
import com.github.squti.guru.Guru;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;




public class adapter_slider extends SliderViewAdapter<adapter_slider.SliderAdapterVH> {

        private Context context;
        private String jenis;
        private List<DataItem_slider> mSliderItems = new ArrayList<>();

    public adapter_slider(Context context, List <DataItem_slider> mList, String jenis) {
        this.mSliderItems = mList;
        this.jenis = jenis;
        this.context = context;
    }

        public void renewItems (List < DataItem_slider > sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

        public void deleteItem ( int position){
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

        public void addItem (DataItem_slider sliderItem){
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

        @Override
        public adapter_slider.SliderAdapterVH onCreateViewHolder (ViewGroup parent){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new adapter_slider.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        DataItem_slider sliderItem = mSliderItems.get(position);




            String server = Guru.getString("data_foto", "default value");
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            viewHolder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            viewHolder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(viewHolder.imageViewBackground);
        }

        @Override
        public int getCount () {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

        class SliderAdapterVH extends ViewHolder {

            View itemView;
            ImageView imageViewBackground;
            ProgressBar progressBar;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                try {
                    imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                    progressBar = itemView.findViewById(R.id.progressBar);

                } catch (Exception e) {

                    Log.i("cek_webview2", "SliderAdapterVH: " + e);

                }


                this.itemView = itemView;
            }
        }



}
