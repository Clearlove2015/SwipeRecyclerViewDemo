package com.odbpo.fenggou.swiperecyclerviewdemo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zc
 * @Time: 2018/9/7 13:35
 * @Desc:
 */
public class InitDataUtil {

    public static List<Integer> getFData(){
        List<Integer> mData = new ArrayList<>();
        for (int i = 0;i<30;i++){
            mData.add(i);
        }
        return mData;
    }

}
