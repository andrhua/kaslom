package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Locale;

public class Settings implements RWable, Resetable{
        private static final String PREFERENCES = "уровень эволюции влюбился в клиентку", LANGUAGE = "language", SFX = "SFX", VIBRATION="vibration";
        private static boolean isSfxEnabled;
        private static boolean isVibrationEnabled;
        private static I18n.Language language;
        private static Preferences preferences;

        public Settings(){
            preferences = Gdx.app.getPreferences(PREFERENCES);
            isSfxEnabled=true;
            isVibrationEnabled=true;
            language= Locale.getDefault().toString().contains("ru")? I18n.Language.RU : I18n.Language.EN;
            read();
        }


        @Override
        public void read() {
            if (preferences.contains(LANGUAGE)) language = I18n.Language.valueOf(preferences.getString(LANGUAGE));
            if (preferences.contains(SFX)) isSfxEnabled = preferences.getBoolean(SFX);
            if (preferences.contains(VIBRATION)) isVibrationEnabled=preferences.getBoolean(VIBRATION);
        }

        @Override
        public void write() {
            preferences.putString(LANGUAGE, language.name());
            preferences.putBoolean(SFX, isSfxEnabled);
            preferences.putBoolean(VIBRATION, isVibrationEnabled);
            preferences.flush();
        }

        @Override
        public void reset() {
            isSfxEnabled=true;
            isVibrationEnabled=true;
            write();
        }

        public static void toggleSfxEnabled() {
            isSfxEnabled = !isSfxEnabled;
        }

        public static void toggleVibrationEnabled() {
            isVibrationEnabled = !isVibrationEnabled;
        }

        public static void setLanguage(I18n.Language language) {
            Settings.language = language;
        }

        public static boolean isSfxEnabled() {
            return isSfxEnabled;
        }

        public static boolean isVibrationEnabled() {
            return isVibrationEnabled;
        }

        public static I18n.Language getLanguage() {
            return language;
        }

    }