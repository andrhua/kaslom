package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class I18n {
    private String language;
    private I18NBundle bundle;
    private FileHandle file;
    private Profile profile;

    public I18n(Profile profile){
        this.profile=profile;
        this.language=profile.language;
        file = Gdx.files.internal("i18n/strings");
        setLanguage(language);
    }

    public boolean setLanguage(String language){
        profile.language=language;
        boolean res=this.language.equals(language);
        this.language=language;
        Locale locale = new Locale(language);
        bundle = I18NBundle.createBundle(file, locale);
        return res;
    }

    public String getLanguage() {
        return language;
    }

    public I18NBundle getBundle() {
        return bundle;
    }

}
