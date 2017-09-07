package com.zy.mydemo.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * Created by  zy on 2017/9/6.
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
public class BlueToothUtils {
    private Context mContext;
    private BluetoothAdapter bluetoothAdapter;

    public BlueToothUtils(Context context) {
        this.mContext = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        init();
    }

    private void init() {
        //检测是蓝牙状态是否打开
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            //蓝牙可用
            //获取蓝牙连接地址


        } else {
            //蓝牙不可用
            opeanBlueTooth();


        }


    }

    /**
     * 打开蓝牙连接
     */
    private void opeanBlueTooth() {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.enable();
        }
    }


}
