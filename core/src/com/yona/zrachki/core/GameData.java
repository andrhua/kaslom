package com.yona.zrachki.core;


import com.yona.zrachki.audio.SFX;

public class GameData {
    public Profile profile;
    public SFX SFX;
    public I18n i18n;
    public Assets assets;

    public GameData(){
        assets=new Assets();
        profile=new Profile();
        SFX =new SFX(profile);
        i18n =new I18n(profile);
    }
}
