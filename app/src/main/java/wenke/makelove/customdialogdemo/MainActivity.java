package wenke.makelove.customdialogdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import wenke.makelove.customdialogexecise.AnimatorType;
import wenke.makelove.customdialogexecise.CustomDialog;


public class MainActivity extends Activity {

    private AnimatorType animatorType;
    private View customView;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = LayoutInflater.from(this).inflate(R.layout.makelove, null);
    }
    public void dialogShow(View v) {
        customDialog = CustomDialog.getInstance(this);
        customView.findViewById(R.id.buttonm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        switch (v.getId()) {
            case R.id.fadein:animatorType = AnimatorType.Fadein;break;
            case R.id.slideright:animatorType = AnimatorType.Slideright;break;
            case R.id.slideleft:animatorType = AnimatorType.Slideleft;break;
            case R.id.slidetop:animatorType = AnimatorType.Slidetop;break;
            case R.id.slideBottom:animatorType = AnimatorType.SlideBottom;break;
            case R.id.newspager:animatorType = AnimatorType.Newspager;break;
            case R.id.fall:animatorType = AnimatorType.Fall;break;
            case R.id.sidefall:animatorType = AnimatorType.Sidefill;break;
            case R.id.fliph:animatorType = AnimatorType.Fliph;break;
            case R.id.flipv:animatorType = AnimatorType.Flipv;break;
            case R.id.rotatebottom:animatorType = AnimatorType.RotateBottom;break;
            case R.id.rotateleft:animatorType = AnimatorType.RotateLeft;break;
            case R.id.slit:animatorType = AnimatorType.Slit;break;
            case R.id.shake:animatorType = AnimatorType.Shake;break;
        }
//        int width = MainActivity.this.getWindowManager().getDefaultDisplay().getWidth();
//        int height = MainActivity.this.getWindowManager().getDefaultDisplay().getHeight();


        //拿到自己写的layout的宽高
        //layout内如果没有子View，无论给layout根部局设置多少dp 这里拿到的宽高都是0
        //但是给layout根部局设置背景图片，我说的是图片，这里拿到的就是图片占有的宽高
        //如果根部局内的所有子view占有的有效宽高小于图片占有的宽高，那就取图片占有的宽高
        //如果根部局内的所有子view占有的有效宽高大于图片占有的宽高，那就取所有子view占有的有效宽高
        //当然如果根部局背景不是设置的图片 而且颜色之类的 一切以所有子view占有的有效宽高为准，此时如果
        //根部局内还没有添加子view  获取的宽高为0
        customView.measure(0, 0);
        System.out.println("makelove");
        int width = customView.getMeasuredWidth();
        int height = customView.getMeasuredHeight();
        customDialog
                //dialog可现实区域的位置
                .setDialogLocation(CustomDialog.CENTER)
                        //dialog的宽高 超过手机屏幕宽高后，会固定为手机屏幕的宽高
                        // .setWidthAndHeight((int) (width * 0.8), (int) (height * 0.3))
                .setWidthAndHeight(width, height)
                        //dialog的透明度和 阴暗程度
                .setAlphaAndDim(0.8f, 0.5f)
                        //dialog的内容view，自己控制展示的内容，灵活性更好
                        //想要什么view  自己随便定义去吧
                .setCustomView(customView)
                        //dialog 弹出时的动画效果，这也是CustomDialog类的亮点
                .setAnimator(animatorType)
                        //动画时间
                .setAnimDuration(1000)
                        //设置点击弹出框以外的部分不会关闭dialog
                .setDialogOutClickable(false)
                .show();
    }
}
