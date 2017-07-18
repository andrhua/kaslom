package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.yona.zrachki.core.GameData;

public class UIHelper {
    private static GameData data;

    public UIHelper(GameData data){
        UIHelper.data =data;
    }

    public static ImageButton initImageButton(String text){
        ImageButton button=new ImageButton(new TextureRegionDrawable(data.assets.uiAtlas.findRegion(text)));
        button.addListener(new TouchListener(button));
        return button;
    }

    public static TextButton initTextButton(String text){
        TextButton button=new TextButton(data.i18n.getBundle().get(text), data.assets.textButtonStyle);
        button.addListener(new TouchListener(button));
        return button;
    }

    public static MyCheckBox initCheckBox(String text){
        MyCheckBox cb=new MyCheckBox(text, data.assets.uiSkin);
        return cb;
    }


}
