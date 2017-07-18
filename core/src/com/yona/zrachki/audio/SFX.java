package com.yona.zrachki.audio;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.yona.zrachki.core.Profile;

public class SFX {
    private static Sound clickPositive, clickNegative;
    public enum SoundType{CLICK_POSITIVE, CLICK_NEGATIVE}
    private static boolean enabled;
    private static Profile profile;

    public SFX(Profile profile){
        loadUISfx();
        SFX.enabled=profile.sfxEnabled;
    }

    private void loadUISfx(){
        clickNegative= Gdx.audio.newSound(Gdx.files.internal("sfx/click_negative.wav"));
        clickPositive=Gdx.audio.newSound(Gdx.files.internal("sfx/click_positive.wav"));
    }

    public void loadGameSfx(){

    }

    public static void play(SoundType soundType){
        if (enabled) {
            switch (soundType) {
                case CLICK_NEGATIVE:clickNegative.play();break;
                case CLICK_POSITIVE:clickPositive.play();break;
            }
        }
    }

    public void setEnabled(boolean enabled){
        SFX.enabled =enabled;
        profile.sfxEnabled=enabled;
    }

    public void disposeGameSfx(){

    }

    public boolean getSFX() {
        return enabled;
    }
}
