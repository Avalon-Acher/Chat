package chat.archer.chat.view;

import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import chat.archer.chat.R;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class SlideLayout extends FrameLayout {
    private Context context;
    public SlideLayout(Context context){
        super(context);
        this.context=context;
    }
    public SlideLayout(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.context=context;
    }
    public SlideLayout(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    private void initViews(){
        this.addView(LayoutInflater.from(context).inflate(R.layout.layout_slide,null));
    }

}
