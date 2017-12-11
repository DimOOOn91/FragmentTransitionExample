package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.view.animation.Animation;

/**
 * author tetiana
 */
public class SimpleAnimationListener implements Animator.AnimatorListener, Animation.AnimationListener {

    private NoParamFunction function;

    public SimpleAnimationListener(@Nullable NoParamFunction function) {
        this.function = function;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        function.accept();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        function.accept();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
