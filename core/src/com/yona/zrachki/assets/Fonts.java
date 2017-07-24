package com.yona.zrachki.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.yona.zrachki.core.Constants;

public class Fonts {

    public static BitmapFont mainRegular, mainScript, mainFormula, logo;

    public Fonts(Skin skin){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/bodoni.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters="абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.color= skin.getColor("black");
        parameter.size= Constants.TEXT_REGULAR;
        mainRegular =generator.generateFont(parameter);
        mainRegular.getData().setScale((float)Constants.HEIGHT/Constants.DEVICE_HEIGHT);

        parameter.size=Constants.TEXT_SCRIPT;
        mainScript=generator.generateFont(parameter);
        mainScript.getData().setScale((float)Constants.HEIGHT/Constants.DEVICE_HEIGHT);

        parameter.size=Constants.TEXT_FORMULA;
        mainFormula=generator.generateFont(parameter);
        mainFormula.getData().setScale((float)Constants.HEIGHT/Constants.DEVICE_HEIGHT);

        generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/votu.ttf"));
        parameter.size=Constants.TEXT_REGULAR;
        logo=generator.generateFont(parameter);
        logo.getData().setScale((float)Constants.HEIGHT/Constants.DEVICE_HEIGHT);
        generator.dispose();
    }
}
