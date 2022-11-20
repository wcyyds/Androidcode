package com.example.constraintlayout;

import com.google.android.material.appbar.AppBarLayout;

public abstract class AppBarStateChangeedListener implements AppBarLayout.OnOffsetChangedListener {
    // AppBar状态
    public enum State {
        EXPANDED,     // 展开
        COLLAPSED,    // 折叠
        IDLE          // 其他
    }

    private State mCurrentState = State.IDLE;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }

    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);

}
