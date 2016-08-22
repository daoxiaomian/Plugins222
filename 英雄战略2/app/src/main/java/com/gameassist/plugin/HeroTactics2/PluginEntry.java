package com.gameassist.plugin.HeroTactics2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.gameassist.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.Random;

public class PluginEntry extends Plugin implements OnClickListener {

    private View pluginView;

    @Override
    public boolean OnPluginCreate() {
        return false;
    }

    public boolean pluginHasUI() {
        return true;
    }

    @Override
    public void OnPlguinDestroy() {
    }

    @Override
    public void OnPluginUIHide() {
    }

    @Override
    public View OnPluginUIShow() {

        if (pluginView == null) {
            pluginView = LayoutInflater.from(getContext()).inflate(
                    R.layout.prompt, null);
            pluginView.findViewById(R.id.close).setOnClickListener(this);
            pluginView.findViewById(R.id.minimum).setOnClickListener(this);
            pluginView.findViewById(R.id.func1).setOnClickListener(this);

        }
        return pluginView;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.close:
                closeSelf();
                break;
            case R.id.minimum:
                hideSelf();
                break;
            case R.id.func1:

                try {
                    ClassLoader clLoader = getTargetApplication().getClassLoader();
                    Class<?> gm3 = clLoader
                            .loadClass("manastone.game.HeroTactics2.AM.Stage");
                    Field fm = gm3.getDeclaredField("m");
                    fm.setAccessible(true);
                    Object om1 = fm.get(gm3);
                    Field fmy = om1.getClass().getDeclaredField("myManastone");
                    long manastone = fmy.getLong(om1);
                    fmy.setAccessible(true);
                    manastone += 1000;
                    fmy.setLong(om1, manastone);


//				Log.i("gameassist", "------------------" +fm.get(gm3));
//				Log.i("gameassist", "------------------" + fmy.get(om1));
//				Log.i("gameassist", "------------------" + aB.getInt(pl));
//				Log.i("gameassist", "-----------cash-------" + z.get(pl));
//				Log.i("gameassist", "------------coins------" + y.get(pl));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
