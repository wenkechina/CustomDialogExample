package wenke.makelove.customdialogexecise.animators;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class FlipH  extends BaseAnimator {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", -90, 0).setDuration(mDuration)

        );
    }
}
