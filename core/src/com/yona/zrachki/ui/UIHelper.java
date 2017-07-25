package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.yona.zrachki.assets.Assets;
import com.yona.zrachki.assets.Fonts;
import com.yona.zrachki.assets.Styles;

import com.yona.zrachki.core.I18n;

public class UIHelper {

    public static ImageButton initImageButton(String styleName){
        ImageButton button=new ImageButton(Assets.uiSkin, styleName);
        button.addListener(new TouchListener(button));
        return button;
    }

    public static TextButton initTextButton(String text){
        TextButton button=new TextButton(I18n.getString(text), Styles.textButtonStyle);
        button.addListener(new TouchListener(button));
        return button;
    }

    public static MyCheckBox initCheckBox(String text){
        return new MyCheckBox(text, Assets.uiSkin);
    }

    public static SelectBox<?> initSelectBox (){
        List.ListStyle ls=new List.ListStyle(
                Fonts.mainRegular,
                Assets.uiSkin.getColor("black"),
                Assets.uiSkin.getColor("grey"),
                Assets.uiSkin.getDrawable("selected"));
        SelectBox.SelectBoxStyle sbs=new SelectBox.SelectBoxStyle(
                Fonts.mainRegular,
                Assets.uiSkin.getColor("black"),
                Assets.uiSkin.getDrawable("background"),
                new ScrollPane.ScrollPaneStyle(),
                ls
        );
        return new SelectBox<Object>(sbs);
    }


}
