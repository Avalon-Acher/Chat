package chat.archer.chat.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chat.archer.chat.R;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class LayoutMoments extends Fragment{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.layout_moments,container,false);
        return rootView;

    }
}
