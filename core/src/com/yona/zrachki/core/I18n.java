package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class I18n {
    public enum Language {EN, RU}
    private static I18NBundle bundle;
    private static FileHandle file;

    public I18n(){
        file = Gdx.files.internal("i18n/strings");
        setLanguage(Settings.getLanguage());
    }

    public static boolean setLanguage(Language language){
        Language prev= Settings.getLanguage();
        Settings.setLanguage(language);
        boolean res=prev.name().equals(language.name());
        Locale locale = new Locale(language.name());
        bundle = I18NBundle.createBundle(file, locale);
        return res;
    }

    public static String getString(String key){
        return bundle.get(key);
    }

}
