package chat.archer.chat.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class AdapterGuideViewPager extends PagerAdapter {

    private Context context;
    private List<View> viewList;

    public AdapterGuideViewPager(Context context,List<View> viewList){
        this.context=context;
        this.viewList=viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
   /* 一般来说，destroyitem在viewpager移除一个item时调用。
    viewpage一般都会缓冲3个item，即一开始就会调用3次instantiateItem,
    当向右滑动，到第3页时，第1页的item会被调用到destroyitem。*/
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView(viewList.get(position));
    }

    //缓存加载 3个item
    public Object instantiateItem(ViewGroup container,int position){
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

}
