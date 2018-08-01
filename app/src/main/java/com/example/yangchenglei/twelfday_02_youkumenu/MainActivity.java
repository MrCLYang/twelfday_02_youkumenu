package com.example.yangchenglei.twelfday_02_youkumenu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/*
*
* 优酷菜单demo
* 功能分析
* 1.内侧的home按钮的点击
* 2.中间菜单的menu的点击事件
* 3.解决动画相应点击事件的响应bug
* 
* 
* */

public class MainActivity extends Activity {

    private ImageView mIvHome;
    private RelativeLayout mRlLevel1;
    private RelativeLayout mRlLevel2;//二级菜单容器?
    private RelativeLayout mRlLevel3;
    //定义一个标记
    private boolean isLevel1Display = true;
    private boolean isLevel2Display = true;
    private boolean isLevel3Display = true;
    private ImageView mIvMenu;
    private int mAnimationCount;//定义动画个数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化view
        initView();
    }

    private void initView() {
        mIvHome = (ImageView) findViewById(R.id.level1_iv_home);
        mRlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
        mRlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);
        mIvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnimationCount > 0) {
                    return;
                }

                //分为三级菜单
                //如果点击home按钮，当二级和三级菜单显示的情况下，二级菜单和三级菜单隐藏
                if (isLevel2Display && isLevel3Display) {
                    //隐藏二级菜单
                    hideLevel(mRlLevel2, 100);
                    //隐藏三级菜单
                    hideLevel(mRlLevel3, 0);
                    //状态的改变
                    isLevel2Display = false;
                    isLevel3Display = false;
                    return;
                }
                //如果点击home按钮，当二级和三级菜单都不显示的情况下，二级菜单显示
                if (!isLevel2Display && !isLevel3Display) {
                    //显示二级菜单
                    showlevel(mRlLevel2, 0);
                    isLevel2Display = true;
                    return;
                }
                //当二级菜单显示的情况，三级不显示的情况下，点击home按钮
                if (isLevel2Display && !isLevel3Display) {
                    hideLevel(mRlLevel2, 0);
                    isLevel2Display = false;
                    return;
                }
            }


        });

        //二级菜单的点击事件
        mIvMenu = (ImageView) findViewById(R.id.level2_iv_menu);
        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnimationCount > 0) {
                    return;
                }

                if (isLevel3Display) {
                    hideLevel(mRlLevel3, 0);
                    isLevel3Display = false;
                    return;
                } else if (!isLevel3Display) {
                    showlevel(mRlLevel3, 0);
                    isLevel3Display = true;
                    return;
                }

            }
        });


    }

    private void showlevel(RelativeLayout container, long startOffset) {
        /*container.setVisibility(View.VISIBLE);*/
        int count = container.getChildCount();
        for (int i = 0; i <count ; i++) {
            container.getChildAt(i).setEnabled(true);
        }
        //动画隐藏形式
        RotateAnimation animation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 1f);
        animation.setDuration(600);
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);
        //监听动画的状态
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画在播放的时候的回调
                mAnimationCount++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //结束的时候回调
                mAnimationCount--;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        container.startAnimation(animation);

    }



    //隐藏菜单的方法
    private void hideLevel(RelativeLayout container, long startOffset) {
       /* container.setVisibility(View.GONE);*/
        /*解决空白点击出现动画效果的bug
        * */
        int count = container.getChildCount();
        for (int i = 0; i <count ; i++) {
            container.getChildAt(i).setEnabled(false);
        }
        RotateAnimation animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 1f);
        animation.setDuration(600);
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);//延时操作
        //监听动画的状态
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画在播放的时候的回调
                mAnimationCount++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //结束的时候回调
                mAnimationCount--;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        container.startAnimation(animation);
    }
}
