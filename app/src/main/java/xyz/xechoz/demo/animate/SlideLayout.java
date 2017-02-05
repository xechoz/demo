package xyz.xechoz.demo.animate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import xyz.xechoz.demo.R;

/**
 * Created by xechoz on 17-2-5.
 * Email: zheng1733@gmail.com
 * 功能：
 * 文档：
 */

public class SlideLayout extends FrameLayout {
    public SlideLayout(@NonNull Context context) {
        super(context);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api=Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void addSlide(AnimateType type, View slideView) {
        final View currentView = getChildAt(0);

        switch (type) {
            case ViewCompat:
                withViewCompat(currentView, slideView);
                break;
            case AnimateProper:
                withViewProperty(currentView, slideView);
                break;
            case AnimateXml:
                withAnimateXml(currentView, slideView);
                break;
        }
    }

    private void withViewProperty(final View from, View to) {
        to.setVisibility(INVISIBLE);
        addView(to);
        ObjectAnimator inAnim = ObjectAnimator.ofFloat(to, "translationY", -getMeasuredHeight(), 0);
        inAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        inAnim.start();
        to.setVisibility(VISIBLE);

        ObjectAnimator outAnim = ObjectAnimator.ofFloat(from, "translationY", 0f, getMeasuredHeight());
        outAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        outAnim.start();
        outAnim.addListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                removeView(from);
            }
        });
    }

    private void withViewCompat(View from, View to) {

        if (to != null) {
            to.setVisibility(INVISIBLE);
            addView(to);
            to.setTranslationY(-getMeasuredHeight());
            to.setVisibility(VISIBLE);

            ViewCompat.animate(to)
                    .translationY(0)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .start();
        }

        if (from != null) {
            ViewCompat.animate(from)
                    .translationY(getMeasuredHeight())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            super.onAnimationEnd(view);

                            view.setVisibility(GONE);
                            removeView(view);
                        }
                    })
                    .setStartDelay(10)
                    .start();
        }
    }


    Animation animationIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down_in);
    Animation animationOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down_out);

    private void withAnimateXml(final View from, final View to) {
        if (to != null) {
            to.setVisibility(GONE);
            addView(to);
            animationIn.setAnimationListener(new AnimationListenerAdapter() {
            });

            to.startAnimation(animationIn);
            to.setVisibility(VISIBLE);
        }

        if (from != null) {
            animationOut.setAnimationListener(new AnimationListenerAdapter() {
                /**
                 * <p>Notifies the end of the animation. This callback is not invoked
                 * for animations with repeat count set to INFINITE.</p>
                 *
                 * @param animation The animation which reached its end.
                 */
                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);

                    // removeView(from)
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            from.setVisibility(GONE);
                            removeView(from);
                        }
                    }, 100);
                }
            });

            from.startAnimation(animationOut);
        }
    }

    public enum AnimateType {
        ViewCompat, AnimateXml, AnimateProper
    }

    private static class AnimationListenerAdapter implements Animation.AnimationListener {

        /**
         * <p>Notifies the start of the animation.</p>
         *
         * @param animation The started animation.
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * <p>Notifies the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param animation The animation which reached its end.
         */
        @Override
        public void onAnimationEnd(Animation animation) {

        }

        /**
         * <p>Notifies the repetition of the animation.</p>
         *
         * @param animation The animation which was repeated.
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
