package com.yona.zrachki.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.assets.Assets;
import com.yona.zrachki.audio.SFX;

import com.yona.zrachki.core.I18n;
import com.yona.zrachki.core.Progress;
import com.yona.zrachki.core.Settings;
import com.yona.zrachki.ui.UIHelper;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class SplashScreen extends BaseScreen {
    private AsyncResult result;
    private AsyncTask task;
    private Image logo;
    private boolean res, updateColor;
    private ColorAction colorAction;

    public SplashScreen(MyGame screenManager) {
        super(screenManager);
        res=false;
        updateColor=false;
    }

    @Override
    public void createStage() {
        backgroundColor=new Color(Color.LIME);
        colorAction=new ColorAction();
        colorAction.setColor(backgroundColor);
        colorAction.setDuration(1);
        Texture texture=new Texture("gfx/logo.png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo=new Image(texture);
        table.defaults().width(200).height(200);
        table.add(logo).align(Align.center);

        task=new AsyncTask() {
            @Override
            public Object call() throws Exception {
                new Assets();
                MyGame.settings=new Settings();
                MyGame.progress=new Progress();
                new SFX();
                new I18n();
                return null;
            }
        };
        AsyncExecutor executor=new AsyncExecutor(1);
        result=executor.submit(task);
        res=result.isDone();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (updateColor) colorAction.act(delta);
    }

    @Override
    public void render() {
        if (result.isDone()&&!res) {
            try {
                task.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            colorAction.setEndColor(Assets.uiSkin.getColor("background"));
            logo.addAction(sequence(
                    fadeOut(.75f),
                    run(new Runnable() {
                        @Override
                        public void run() {
                            updateColor=true;
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    setScreen(new MenuScreen(screenManager));
                                }
                            }, 1);

                        }
                    })
            ));
            res=true;
        }
    }

    @Override
    void setScreen(BaseScreen screen) {
        screenManager.setScreen(screen);
    }

    @Override
    void enterAnimation() {

    }
}
