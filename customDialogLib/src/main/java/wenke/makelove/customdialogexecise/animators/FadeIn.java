package wenke.makelove.customdialogexecise.animators;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class FadeIn extends BaseAnimator {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view,"alpha",0,1).setDuration(mDuration)

        );
    }
}
