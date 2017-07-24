package com.yona.zrachki.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Styles {
    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle titleLabelStyle, regularLabelStyle, formulaLabelStyle, scriptLabelStyle;

    public Styles(Skin skin){
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font= Fonts.mainRegular;
        textButtonStyle.fontColor= skin.getColor("black");

        titleLabelStyle=new Label.LabelStyle( Fonts.logo, Color.BLACK);
        regularLabelStyle =new Label.LabelStyle( Fonts.mainRegular, Color.BLACK);
        formulaLabelStyle =new Label.LabelStyle( Fonts.mainFormula, Color.BLACK);
        scriptLabelStyle=new Label.LabelStyle( Fonts.mainScript, Color.BLACK);
    }
}
