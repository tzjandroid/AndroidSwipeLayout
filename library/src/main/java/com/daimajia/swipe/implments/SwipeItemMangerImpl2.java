package com.daimajia.swipe.implments;

import android.view.View;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.interfaces.SwipeAdapterInterface;

/**
 * 可以没有 SwipeLayout
 */
public class SwipeItemMangerImpl2 extends SwipeItemMangerImpl{

    public SwipeItemMangerImpl2(SwipeAdapterInterface swipeAdapterInterface) {
        super(swipeAdapterInterface);
    }

    public void onCreate(View view,int position){
        int resId = swipeAdapterInterface.getSwipeLayoutResourceId(position);
        if (resId<=0){
            return;
        }
        view.setTag(resId,view.findViewById(resId));
    }
    @Override
    public void bind(View view, int position) {
        int resId = swipeAdapterInterface.getSwipeLayoutResourceId(position);
        if (resId<=0){
            return;
        }
        int tagId = resId+1;//防止 view 为 SwipeLayout 覆盖掉
        SwipeLayout swipeLayout = (SwipeLayout) view.getTag(resId);
        if (swipeLayout == null)
            throw new IllegalStateException("can not find SwipeLayout in target view");

        if (swipeLayout.getTag(tagId) == null) {
            OnLayoutListener onLayoutListener = new OnLayoutListener(position);
            SwipeMemory swipeMemory = new SwipeMemory(position);
            swipeLayout.addSwipeListener(swipeMemory);
            swipeLayout.addOnLayoutListener(onLayoutListener);
            swipeLayout.setTag(tagId, new ValueBox(position, swipeMemory, onLayoutListener));
            mShownLayouts.add(swipeLayout);
        } else {
            ValueBox valueBox = (ValueBox) swipeLayout.getTag(tagId);
            valueBox.swipeMemory.setPosition(position);
            valueBox.onLayoutListener.setPosition(position);
            valueBox.position = position;
        }
    }
}
