package chat.archer.chat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import chat.archer.chat.R;

/**
 * Created by 连浩逵 on 2017/6/9.
 */

public class PicAndTextBtn extends LinearLayout {
    private Context context;

    private ImageView imageView;
    private TextView textView;

    private Drawable image;
    private Drawable imageBackground;

    private String text;
    private float textSize;
    private int textColor;
    private Drawable textBackground;

    private picAndTextBtnClickListener listener;

    public PicAndTextBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        findAttrs(attrs);
        initView();
        setPicAndTextBtnLayoutParams();
        setClickListener();
    }

    public interface picAndTextBtnClickListener{
        void onClick(View view);
    }

    public void setOnClickListener(picAndTextBtnClickListener listener){
        this.listener=listener;
    }
    public void setImageView(int id){
        imageView.setImageResource(id);
    }

    private void setClickListener(){
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
    }

    private void setPicAndTextBtnLayoutParams(){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(20,0,0,0);

        LayoutParams imageLayoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(imageView,imageLayoutParams);

        LayoutParams textViewLayoutParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        addView(textView,textViewLayoutParams);
    }

    private void initView(){
        imageView=new ImageView(context);
        textView=new TextView(context);

        imageView.setImageDrawable(image);
        imageView.setBackground(imageBackground);

        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setBackground(textBackground);

        textView.setGravity(Gravity.RIGHT);
    }

    private void findAttrs(AttributeSet attributeSet){
        TypedArray typedArray=context.obtainStyledAttributes(attributeSet, R.styleable.PicAndTextBtn);

        image=typedArray.getDrawable(R.styleable.PicAndTextBtn_pic);
        imageBackground=typedArray.getDrawable(R.styleable.PicAndTextBtn_picBackground);

        text=typedArray.getString(R.styleable.PicAndTextBtn_text);
        textSize=typedArray.getDimension(R.styleable.PicAndTextBtn_textSize,0);
        textColor=typedArray.getColor(R.styleable.PicAndTextBtn_textColor,0);
        textBackground=typedArray.getDrawable(R.styleable.PicAndTextBtn_textBackground);
        typedArray.recycle();

    }

}
