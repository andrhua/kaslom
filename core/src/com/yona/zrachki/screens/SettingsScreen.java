package com.yona.zrachki.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.assets.Styles;
import com.yona.zrachki.core.GameData;
import com.yona.zrachki.ui.MyCheckBox;

import static com.yona.zrachki.ui.UIHelper.initCheckBox;
import static com.yona.zrachki.ui.UIHelper.initImageButton;

class SettingsScreen extends BaseScreen{
    private MyCheckBox soundBox, vibroBox;
    private SelectBox complexityBox, languageBox;
    private TextButton languageButton, eraseButton;
    private Label titleLabel;
    private ImageButton backButton;

    SettingsScreen(MyGame screenManager, GameData data) {
        super(screenManager, data);
    }

    @Override
    public void createStage() {
        soundBox=initCheckBox(data.i18n.getBundle().get("sound"));
        soundBox.getLabelCell().padRight(40);
        soundBox.setChecked(data.profile.sfxEnabled);
        soundBox.getImageCell().getActor().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                data.SFX.setEnabled(soundBox.isChecked());
            }
        });
        vibroBox=initCheckBox(data.i18n.getBundle().get("vibration"));
        vibroBox.getLabelCell().padRight(40);
        vibroBox.setChecked(data.profile.vibrationEnabled);
        vibroBox.getImageCell().getActor().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                data.profile.vibrationEnabled=vibroBox.isChecked();
            }
        });

        languageBox=new SelectBox(data.assets.uiSkin);
        languageBox.setItems(data.i18n.getBundle().get("en"), data.i18n.getBundle().get("ru"));
        languageBox.setSelectedIndex(data.profile.language.equals("ru")?1:0);
        languageBox.getList().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                int lang=languageBox.getSelectedIndex();
                if (!data.i18n.setLanguage(lang==0?"en":"ru"))
                    setScreen(new MenuScreen(screenManager, data));
            }
        });

        backButton=initImageButton("back");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new MenuScreen(screenManager, data));
            }
        });

        titleLabel=new Label(data.i18n.getBundle().get("settings"), Styles.regularLabelStyle);
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
