package wenke.makelove.customdialogexecise.animators;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class Fall extends BaseAnimator {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view,"scaleY",2,1.5f,1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

        );
    }
}
