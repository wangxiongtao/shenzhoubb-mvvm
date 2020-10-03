package com.dawn.lib_common.util;


import com.youth.banner.util.LogUtils;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class FragmentUtil {
    /**
     * 用于 多个Fragment之间show或者hide
     *
     * @param manager
     * @param showPosition    显示fragment的位置也就是在数组中的位置
     * @param fragments       全部的fragment数组
     * @param containerViewId 替换的布局控件id
     */
    public static void showFragment(FragmentManager manager, int showPosition, List<Fragment> fragments, int containerViewId) {
        LogUtils.e("==showFragment===>" + showPosition);
        try {
            FragmentTransaction transaction = manager.beginTransaction();
            int size = fragments.size();
            if (showPosition < size && size > 0) {
                for (int i = 0; i < size; i++) {
                    Fragment fragment = fragments.get(i);
                    if (fragment != null && fragment.isAdded()) {
                        transaction.hide(fragment);
                    }
                }

                Fragment showFragment = fragments.get(showPosition);
                if (showFragment == null) {
                    return;
                }
                if (showFragment.isAdded()) {
                    transaction.show(showFragment);
                } else {
                    transaction.add(containerViewId, showFragment);
                }
                transaction.commitAllowingStateLoss();
            }
        } catch (Exception e) {
            LogUtils.e("==showFragment===Exception===>" + e.getLocalizedMessage());
        }

    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment, int containerViewId) {
        try {
            manager.beginTransaction().replace(containerViewId, fragment).commit();
        } catch (Exception e) {
            LogUtils.e("==replaceFragment===Exception===>" + e.getLocalizedMessage());
        }
    }

    public static void showFragment(FragmentManager manager, Fragment showFragment, int containerViewId) {
        LogUtils.e("==showFragment===>" + showFragment);
        try {
            FragmentTransaction transaction = manager.beginTransaction();
            if (showFragment.isAdded()) {
                transaction.show(showFragment);
            } else {
                transaction.add(containerViewId, showFragment);
            }
            transaction.commit();
        } catch (Exception e) {
            LogUtils.e("==showFragment===Exception===>" + e.getLocalizedMessage());
        }

    }

    public static void hideFragment(FragmentManager manager, Fragment hideFragment) {
        if (hideFragment == null) {
            return;
        }
        manager.beginTransaction().hide(hideFragment).commit();
    }

}
