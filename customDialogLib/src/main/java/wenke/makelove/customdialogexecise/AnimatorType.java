package wenke.makelove.customdialogexecise;


import wenke.makelove.customdialogexecise.animators.BaseAnimator;
import wenke.makelove.customdialogexecise.animators.FadeIn;
import wenke.makelove.customdialogexecise.animators.FlipH;
import wenke.makelove.customdialogexecise.animators.FlipV;
import wenke.makelove.customdialogexecise.animators.NewsPaper;
import wenke.makelove.customdialogexecise.animators.SideFall;
import wenke.makelove.customdialogexecise.animators.SlideLeft;
import wenke.makelove.customdialogexecise.animators.SlideRight;
import wenke.makelove.customdialogexecise.animators.SlideTop;
import wenke.makelove.customdialogexecise.animators.SlideBottom;
import wenke.makelove.customdialogexecise.animators.Fall;
import wenke.makelove.customdialogexecise.animators.RotateBottom;
import wenke.makelove.customdialogexecise.animators.RotateLeft;
import wenke.makelove.customdialogexecise.animators.Slit;
import wenke.makelove.customdialogexecise.animators.Shake;

public enum AnimatorType {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseAnimator> animatorClazz;

    AnimatorType(Class<? extends BaseAnimator> mclass) {
        animatorClazz = mclass;
    }

    public BaseAnimator getAnimator() {
        BaseAnimator baseAnimator;
	try {
		baseAnimator = animatorClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		throw new Error("Can not init animatorClazz instance");
	}
	return baseAnimator;
    }
}
