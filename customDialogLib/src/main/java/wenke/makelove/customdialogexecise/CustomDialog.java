package wenke.makelove.customdialogexecise;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import wenke.makelove.customdialogexecise.animators.BaseAnimator;


/**
 * 弹出框的位置，是根据dialog_layout布局中子framelayout在父relativelayout中的位置而定
 */
public class CustomDialog extends Dialog implements DialogInterface {
    public static final  int CENTER = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;

    private static Context tmpContext;

    private AnimatorType animatorType = null;


    private RelativeLayout mRelativeLayoutView;


    private View mDialogView;


    private int mDuration = 700;


    private boolean isCancelable = true;

    private static CustomDialog instance;
    private FrameLayout customPanel;

    public CustomDialog(Context context) {
        super(context);
        init(context);

    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //重新设置dialog的宽高为match_parent，如果不设置，设置的动画展示效果会发生冲突
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;


        getWindow().setAttributes(params);

    }
    public static CustomDialog getInstance(Context context) {

        if (instance == null || !tmpContext.equals(context)) {
            synchronized (CustomDialog.class) {
                if (instance == null || !tmpContext.equals(context)) {
                    instance = new CustomDialog(context, R.style.custom_dialog);
                }
            }
        }
        tmpContext = context;
        return instance;

    }

    public CustomDialog setAlphaAndDim(float alpha, float dim) {
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.alpha = alpha;
        attributes.dimAmount = dim;
        this.getWindow().setAttributes(attributes);
        return this;
    }

    public CustomDialog setAnimator(AnimatorType type) {
        this.animatorType = type;
        return this;
    }

    public CustomDialog setDialogLocation(int location) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) customPanel.getLayoutParams();
        switch (location) {
            case CENTER:
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                break;
            case LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case TOP:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
            case BOTTOM:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
        }

        customPanel.requestLayout();
        return this;
    }

    public CustomDialog setWidthAndHeight(int width, int height) {
        ViewGroup.LayoutParams layoutParams = customPanel.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        customPanel.requestLayout();
        return this;
    }

    private void init(Context context) {
        mDialogView = View.inflate(context, R.layout.dialog_layout, null);

        mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(R.id.main);
        customPanel = (FrameLayout) mDialogView.findViewById(R.id.customPanel);
        setContentView(mDialogView);

        this.setCanceledOnTouchOutside(true);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                if (animatorType == null) {
                    animatorType = AnimatorType.Slidetop;
                }
                start(animatorType);
            }
        });

        //处理点击dialog弹框外的区域无法关闭问题
        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //点击dialog弹框外的区域关闭，点击dialog弹框内的任何区域都不会关闭
                customPanel.setOnClickListener(null);
                if (isCancelable) dismiss();
            }
        });
    }

    public CustomDialog setDialogOutClickable(boolean makelove) {
        isCancelable = makelove;
        return this;
    }

    public CustomDialog setAnimDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public CustomDialog setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (customPanel.getChildCount() > 0) {
            customPanel.removeAllViews();
        }
        customPanel.addView(customView);
        customPanel.setVisibility(View.VISIBLE);
        return this;
    }

    public CustomDialog setCustomView(View view) {
        if (customPanel.getChildCount() > 0) {
            customPanel.removeAllViews();
        }

        customPanel.addView(view);
        customPanel.setVisibility(View.VISIBLE);
        return this;
    }


    private void start(AnimatorType type) {
        BaseAnimator animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }

}
