package com.zy.mydemo.adapter;


import android.util.Log;
import android.widget.TextView;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseAdapter;
import com.zy.mydemo.base.BaseViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by  zy on 2017/8/29.
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */
public class VideoListAdapter extends BaseAdapter<Map<String, String>> {
    public VideoListAdapter(List list, int LayoutId) {
        super(list, LayoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        Map<String, String> stringStringMap = mList.get(position);
        stringStringMap.toString();

        TextView name = holder.getView(R.id.tv_item_name);
        String data = stringStringMap.get("_display_name");
        name.setText("名称：" + data);
        TextView size = holder.getView(R.id.tv_item_size);
        String data1 = stringStringMap.get("_size");
        String sizeStr = "";
        long l = Long.parseLong(data1);
        long l1 = l / 1024;
        if (l1 > 1024) {
            long l2 = l1 / 1024;
            if (l2 > 1024) {
                long l3 = l2 / 1024;
                if (l3 > 1024) {
                    long l4 = l3 / 1024;
                    sizeStr = l4 + "Tb";
                } else {
                    sizeStr = l3 + "Gb";
                }
            } else {
                sizeStr = l2 + "Mb";
            }
        } else {
            sizeStr = l1 + "Kb";
        }


        size.setText("大小：" + sizeStr);
        TextView uri = holder.getView(R.id.tv_item_url);
        String data2 = stringStringMap.get("_data");
        uri.setText("位置：" + data2);

    }
}
