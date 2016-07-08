package com.example.ruolan.letgo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MyToolbar extends Toolbar {

    //布局加载器
    private LayoutInflater mInflater;
    //标题
    private TextView tvTitle;
    //左右图片
    private ImageView mLeftIcon,mRightIcon;


    private View mView;

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

        setContentInsetsRelative(10,10);

        if (attrs !=null){
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(),attrs,R.styleable.MyToolbar,defStyleAttr,0);

            final Drawable leftIcon = a.getDrawable(R.styleable.MyToolbar_leftIcon);
            if (leftIcon !=null){
                setLeftIcon(leftIcon);
            }

            final Drawable rightIcon = a.getDrawable(R.styleable.MyToolbar_rightIcon);
            if (rightIcon != null){
                setRightIcon(rightIcon);
            }

            final String title= a.getString(R.styleable.MyToolbar_Title);
            if (title != null){
                showTitleView();
            }

        }
    }

    //显示标题
    public void showTitleView() {
        if (tvTitle != null){
            tvTitle.setVisibility(VISIBLE);
        }
    }

    //显示标题
    public void hideTitleView() {
        if (tvTitle != null){
            tvTitle.setVisibility(GONE);
        }
    }


    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (tvTitle !=null){
            tvTitle.setText(title);
            showTitleView();
        }
    }

    /**
     * 设置右侧的图片
     * @param icon
     */
    public void setRightIcon(Drawable icon) {
        if (icon != null){
            mRightIcon.setImageDrawable(icon);
            mRightIcon.setVisibility(VISIBLE);
        }
    }

    /**
     * 设置左侧的图片
     * @param icon
     */
    public void setLeftIcon(Drawable icon) {
        if (icon!=null){
            mLeftIcon.setImageDrawable(icon);
            mLeftIcon.setVisibility(VISIBLE);
        }
    }

    private void initView() {
        if (mView == null){
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar,null);

            //绑定控件
            tvTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mLeftIcon = (ImageView) mView.findViewById(R.id.toolbar_left_button);
            mRightIcon = (ImageView) mView.findViewById(R.id.toolbar_right_button);
            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
        }
    }



    /**
     * 设置左侧的按钮的点击事件
     * @param listener
     */
    public void setLeftButtonOnClickListener(OnClickListener listener){
        mLeftIcon.setOnClickListener(listener);
    }

    /**
     * 设置右侧按钮的点击事件
     * @param listener
     */
    public void setRightButtonOnClickListener(OnClickListener listener){
        mRightIcon.setOnClickListener(listener);
    }



}
