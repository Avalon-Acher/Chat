package chat.archer.chat.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chat.archer.chat.R;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class LayoutChats extends Fragment {
    private View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        rootView=inflater.inflate(R.layout.layout_chats,container,false);
        return rootView;
    }
}
