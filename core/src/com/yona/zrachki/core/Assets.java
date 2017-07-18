package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {

    public BitmapFont main30, main20, main15, main10, logo;
    public TextButton.TextButtonStyle textButtonStyle;
    public Label.LabelStyle titleLabelStyle, trackLabelStyle, recordLabelStyle,
            lockedPreviewStyle, logLabelStyle, scriptLabelStyle;
    public Skin uiSkin;
    public TextureAtlas uiAtlas;
    public CheckBox.CheckBoxStyle checkBoxStyle;

    public Assets(){
        uiSkin=new Skin(Gdx.files.internal("gfx/skin/ui.json"));

        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/bodoni.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size= Constants.HEIGHT/15;
        parameter.characters="абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.color= uiSkin.getColor("black");
        main15 =generator.generateFont(parameter);
        parameter.size= Constants.HEIGHT/10;
        main10 =generator.generateFont(parameter);
        parameter.size=Constants.HEIGHT/20;
        main20=generator.generateFont(parameter);
        parameter.size=Constants.HEIGHT/30;
        main30 =generator.generateFont(parameter);

        generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/votu.ttf"));
        parameter.size=Constants.HEIGHT/16;
        logo=generator.generateFont(parameter);
        generator.dispose();

        uiSkin.add("font", main20);

        titleLabelStyle=new Label.LabelStyle(logo, Color.BLACK);
        trackLabelStyle =new Label.LabelStyle(main15, Color.BLACK);
        recordLabelStyle=new Label.LabelStyle(main20, Color.BLACK);
        logLabelStyle=new Label.LabelStyle(main15, Color.BLACK);
        scriptLabelStyle=new Label.LabelStyle(main30, Color.BLACK);

        uiAtlas=new TextureAtlas(Gdx.files.internal("gfx/atlases/ui.atlas"));
        lockedPreviewStyle=new Label.LabelStyle(main15, Color.LIGHT_GRAY);
        checkBoxStyle=new CheckBox.CheckBoxStyle();
    }

    public Drawable atlasToDrawable(TextureRegion textureRegion) {
        return new TextureRegionDrawable(textureRegion);
    }
}
