package com.yona.zrachki.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Styles {
    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle titleLabelStyle, regularLabelStyle, formulaLabelStyle, scriptLabelStyle;

    public Styles(Skin skin){
        Color blackColor=skin.getColor("black");
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font= Fonts.mainRegular;
        textButtonStyle.fontColor= blackColor;

        titleLabelStyle=new Label.LabelStyle( Fonts.logo, blackColor);
        regularLabelStyle =new Label.LabelStyle( Fonts.mainRegular, blackColor);
        formulaLabelStyle =new Label.LabelStyle( Fonts.mainFormula, blackColor);
        scriptLabelStyle=new Label.LabelStyle( Fonts.mainScript, blackColor);

    }
}
