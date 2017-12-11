package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;

@RequiresApi(21)
public class CardTransition extends Transition {


    private static final String BOUNDS = "view_bounds";
    private static final String RADIUS = "view_radius";

    private static final String[] PROPS = {BOUNDS, RADIUS};

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        Log.d("WTF", String.format("captureStartValues: \nview=%s", transitionValues.view));
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        Log.d("WTF", String.format("captureEndValues: \nview=%s", transitionValues.view));
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;
        Rect bounds = new Rect();
        bounds.left = view.getLeft();
        bounds.right = view.getRight();
        bounds.top = view.getTop();
        bounds.bottom = view.getBottom();

        values.values.put(BOUNDS, bounds);

        if (view instanceof RoundedImageView) {
            float radius = ((RoundedImageView) view).getCornerRadius();
            values.values.put(RADIUS, radius);
        }
    }

    @Override
    public String[] getTransitionProperties() {
        return PROPS;
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Log.d("WTF", String.format("createAnimator: \nstartValues=%s \nendValues=%s", startValues == null ? null : startValues.view, endValues == null ? null : endValues.view));
        if (startValues == null || endValues == null) {
            return null;
        }

        final View view = endValues.view;
        Rect startRect = (Rect) startValues.values.get(BOUNDS);
        final Rect endRect = (Rect) endValues.values.get(BOUNDS);

        AnimatorSet cardMoveAnimator = new AnimatorSet();

        if (!startRect.equals(endRect)) {
            ValueAnimator topAnimator = ValueAnimator.ofInt(startRect.top, startRect.top);
            topAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    view.setTop(value);
                }
            });

            setRemoveListener(topAnimator);

            ValueAnimator bottomAnimator = ValueAnimator.ofInt(startRect.bottom, startRect.bottom);
            bottomAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    view.setBottom(value);
                }
            });

            setRemoveListener(bottomAnimator);

            ValueAnimator leftAnimator = ValueAnimator.ofInt(startRect.left, startRect.left);
            leftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    view.setLeft(value);
                }
            });

            setRemoveListener(leftAnimator);


            ValueAnimator rightAnimator = ValueAnimator.ofInt(startRect.right, startRect.right);
            rightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    view.setRight(value);
                }
            });

            setRemoveListener(rightAnimator);

            cardMoveAnimator.playTogether(topAnimator, bottomAnimator, leftAnimator, rightAnimator);
        }

        if (!isAnimatorSetEmpty(cardMoveAnimator) && view instanceof RoundedImageView) {
            final RoundedImageView roundedView = (RoundedImageView) view;
            float startRadius = (float) startValues.values.get(RADIUS);
            float endRadius = (float) endValues.values.get(RADIUS);
            if (startRadius != endRadius) {
                ValueAnimator radiusAnimator = ValueAnimator.ofFloat(startRadius, endRadius);
                radiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (float) valueAnimator.getAnimatedValue();
                        roundedView.setCornerRadius(value);
                    }
                });

                setRemoveListener(radiusAnimator);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(cardMoveAnimator).with(radiusAnimator);
                return animatorSet;
            }
        }

        return isAnimatorSetEmpty(cardMoveAnimator) ? null : cardMoveAnimator;
    }

    private boolean isAnimatorSetEmpty(AnimatorSet cardMoveAnimator) {
        return cardMoveAnimator.getChildAnimations().isEmpty();
    }

    private void setRemoveListener(final ValueAnimator animator) {
        animator.addListener(new SimpleAnimationListener(new NoParamFunction() {
            @Override
            public void accept() {
                animator.removeAllListeners();
            }
        }));
    }

}
