package com.songdehuai.commonlib.utils.imagepicker.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.songdehuai.commonlib.R;
import com.songdehuai.commonlib.utils.imagepicker.ImageItem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 多图选择适配器
 *
 * @author songdehuai
 */
public class MultImageReAdapter extends RecyclerView.Adapter<MultImageReAdapter.ViewHolder> {

    private Activity activity;
    private CopyOnWriteArrayList<ImageItem> imageItemList;
    private OnSelectImageListener onSelectImageListener;

    public MultImageReAdapter(Activity activity) {
        this.activity = activity;
        this.imageItemList = new CopyOnWriteArrayList<>();
    }

    public void setImageItemList(CopyOnWriteArrayList<ImageItem> imageItemList) {
        this.imageItemList = imageItemList;
        notifyDataSetChanged();
    }

    Set<ImageItem> selectImages = new LinkedHashSet<>();

    public int getSelectImageCount() {
        return selectImages.size();
    }

    public Set<ImageItem> getSelectImages() {
        return selectImages;
    }

    public void setOnSelectImageListener(OnSelectImageListener onSelectImageListener) {
        this.onSelectImageListener = onSelectImageListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageItem imageItem = imageItemList.get(position);
        Glide.with(activity).load(imageItem.getFilePath()).thumbnail(0.1f).into(holder.imageView);
        holder.checkBox.setChecked(imageItem.isCheck());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            imageItemList.get(position).setCheck(!imageItem.isCheck());
            if (isChecked) {
                selectImages.add(imageItem);
            } else {
                selectImages.remove(imageItem);
            }
            if (onSelectImageListener != null) {
                onSelectImageListener.onSelect(isChecked, selectImages);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    public interface OnSelectImageListener {

        void onSelect(boolean isChecked, Set<ImageItem> selectImages);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
