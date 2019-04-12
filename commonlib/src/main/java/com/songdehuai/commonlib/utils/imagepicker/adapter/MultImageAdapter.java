package com.songdehuai.commonlib.utils.imagepicker.adapter;

import android.app.Activity;
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

/**
 * 多图选择适配器
 *
 * @author songdehuai
 */
public class MultImageAdapter extends BaseAdapter {

    private Activity activity;
    private List<ImageItem> imageItemList;
    private OnSelectImageListener onSelectImageListener;

    public MultImageAdapter(Activity activity) {
        this.activity = activity;
        this.imageItemList = new ArrayList<>();
    }

    public void setImageItemList(List<ImageItem> imageItemList) {
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

    @Override
    public int getCount() {
        return imageItemList.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return imageItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == viewHolder) {
            convertView = View.inflate(activity, R.layout.item_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageItem imageItem = imageItemList.get(position);
        Glide.with(activity).load(imageItem.getFilePath()).into(viewHolder.imageView);
        viewHolder.checkBox.setChecked(imageItem.isCheck());
        viewHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
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
        return convertView;
    }

    public interface OnSelectImageListener {

        void onSelect(boolean isChecked, Set<ImageItem> selectImages);
    }

    protected class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            imageView = itemView.findViewById(R.id.image);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
