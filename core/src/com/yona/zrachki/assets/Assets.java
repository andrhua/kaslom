package com.yona.zrachki.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public Skin uiSkin;
    public TextureAtlas uiAtlas;

    public Assets(){
        uiSkin=new Skin(Gdx.files.internal("gfx/skin/ui.json"));
        new Fonts(uiSkin);
        uiSkin.add("font", Fonts.mainRegular);
        new Styles(uiSkin);
        uiAtlas=new TextureAtlas(Gdx.files.internal("gfx/atlases/ui.atlas"));
    }

}
