package com.gameassist.plugin.ami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.gameassist.plugin.Plugin;

import java.lang.reflect.Field;

public class PluginEntry extends Plugin implements OnClickListener {

    private View pluginView;

    @Override
    public boolean OnPluginCreate() {
        return false;
    }

    public boolean pluginHasUI() {
        return false;
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
                    Class<?> gm = clLoader.loadClass("com.ami.BattleCity.Manager.GameManager");
                    Field life = gm.getDeclaredField("mCurrentTankLifes");
                    life.setAccessible(true);
                    int l = life.getInt(gm);
                    l += 100;
                    life.setInt(gm, l);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Toast.makeText(v.getContext(), "加生命×100", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}