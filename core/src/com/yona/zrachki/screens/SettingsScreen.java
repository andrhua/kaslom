package com.yona.zrachki.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.assets.Assets;
import com.yona.zrachki.assets.Styles;
import com.yona.zrachki.audio.SFX;

import com.yona.zrachki.core.I18n;
import com.yona.zrachki.core.Settings;
import com.yona.zrachki.ui.MyCheckBox;
import com.yona.zrachki.ui.UIHelper;

import static com.yona.zrachki.ui.UIHelper.initCheckBox;
import static com.yona.zrachki.ui.UIHelper.initImageButton;

class SettingsScreen extends BaseScreen{
    private MyCheckBox soundBox, vibroBox;
    private SelectBox complexityBox, languageBox;
    private TextButton languageButton, eraseButton;
    private Label titleLabel;
    private ImageButton backButton;

    SettingsScreen(MyGame screenManager) {
        super(screenManager);
    }

    @Override
    public void createStage() {
        soundBox=initCheckBox(I18n.getString("sound"));
        soundBox.getLabelCell().padRight(40);
        soundBox.setChecked(Settings.isSfxEnabled());
        soundBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                SFX.play(SFX.SoundType.CLICK_POSITIVE);
                Settings.toggleSfxEnabled();
            }
        });
        vibroBox=initCheckBox(I18n.getString("vibration"));
        vibroBox.getLabelCell().padRight(40);
        vibroBox.setChecked(Settings.isVibrationEnabled());
        vibroBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                SFX.play(SFX.SoundType.CLICK_POSITIVE);
                Settings.toggleVibrationEnabled();
            }
        });

        languageBox= UIHelper.initSelectBox();
        languageBox.setItems(I18n.getString("en"), I18n.getString("ru"));
        languageBox.setSelectedIndex(Settings.getLanguage()== I18n.Language.EN?0:1);
        languageBox.getList().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                int lang=languageBox.getSelectedIndex();
                if (!I18n.setLanguage(lang==0? I18n.Language.EN: I18n.Language.RU))
                    setScreen(new MenuScreen(screenManager));
            }
        });

        backButton=initImageButton("back");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new MenuScreen(screenManager));
            }
        });

        titleLabel=new Label(I18n.getString("settings"), Styles.regularLabelStyle);
        table.setWidth(.7f*width);
        table.add(titleLabel).row();
        table.add(soundBox.getTable()).left().expandY().row();
        table.add(vibroBox.getTable()).left().expandY().row();
        table.add(languageBox).expandY().row();
        table.add(backButton).width(64);
    }

    @Override
    public void render() {

    }
}
