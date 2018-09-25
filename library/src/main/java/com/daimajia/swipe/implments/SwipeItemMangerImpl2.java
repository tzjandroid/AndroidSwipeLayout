package com.daimajia.swipe.implments;

import android.view.View;

import com.daimajia.swipe.interfaces.SwipeAdapterInterface;

/**
 * 可以没有 SwipeLayout
 */
public class SwipeItemMangerImpl2 extends SwipeItemMangerImpl{

    public SwipeItemMangerImpl2(SwipeAdapterInterface swipeAdapterInterface) {
        super(swipeAdapterInterface);
    }

    @Override
    public void bind(View view, int position) {
        int resId = swipeAdapterInterface.getSwipeLayoutResourceId(position);
        if (resId<=0){
            return;
        }
        super.bind(view, position);
    }
}
