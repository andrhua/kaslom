package com.yona.zrachki.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static Skin uiSkin;

    public Assets(){
        uiSkin=new Skin(Gdx.files.internal("gfx/skin/ui.json"));

        new Fonts(uiSkin);
        new Styles(uiSkin);
    }

}
