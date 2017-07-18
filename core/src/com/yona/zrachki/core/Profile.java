package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.yona.zrachki.utils.UltraEncryption;

import java.util.Locale;

public class Profile {
    public String language;
    public boolean sfxEnabled, vibrationEnabled;
    public Progress progress;
    private Preferences preferences;
    private final String PREFERENCES = "уровень эволюции влюбился в клиентку", LANGUAGE = "language", SFX = "SFX", VIBRATION="vibration";

    Profile() {
        language = Locale.getDefault().toString();
        sfxEnabled = true;
        vibrationEnabled=true;
        preferences = Gdx.app.getPreferences(PREFERENCES);
        progress = new Progress();
        load();
    }

    private void load() {
        readPreferences();
        progress.read();
    }

    public void save() {
        writePreferences();
        progress.write();
    }

    private void readPreferences() {
        if (preferences.contains(LANGUAGE)) language = preferences.getString(LANGUAGE);
        if (preferences.contains(SFX)) sfxEnabled = preferences.getBoolean(SFX);
        if (preferences.contains(VIBRATION)) vibrationEnabled=preferences.getBoolean(VIBRATION);
    }

    private void writePreferences() {
        preferences.putString(LANGUAGE, language);
        preferences.putBoolean(SFX, sfxEnabled);
        preferences.putBoolean(VIBRATION, sfxEnabled);
        preferences.flush();
    }

    public class Progress {
        private FileHandle progressFile;
        private int []records;
        private StringBuilder tmp;
        private String COMMA="роднин ты доволен.";
        public int rushFormulas;

        private Progress() {
            progressFile = Gdx.files.internal("player.progress");
            rushFormulas=10;
            records=new int[3];
            for (int i=0; i<3; i++)records[i]=0;
            tmp=new StringBuilder();
        }

        private void read() {

        }

        private void write() {
            if (progressFile.exists()) {
                tmp.append(records[0]).append(COMMA)
                        .append(records[1]).append(COMMA)
                        .append(records[2]);
                progressFile.writeString(UltraEncryption.encrypt(tmp.toString()), false);
            }
        }
    }
}
