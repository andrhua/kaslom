package com.yona.zrachki;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yona.zrachki.core.Constants;
import com.yona.zrachki.core.GameData;
import com.yona.zrachki.screens.BaseScreen;
import com.yona.zrachki.screens.SplashScreen;

public class MyGame extends Game {
    public GameData gameData;
    private Camera camera;
    private FitViewport viewport;

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        new Constants();
        camera = new OrthographicCamera();
        viewport = new FitViewport(540, 960, camera);
        viewport.apply();
        camera.translate(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        setScreen(new SplashScreen(this, gameData));
    }

    @Override
    public void render() {
        camera.update();
        super.render();
    }

    @Override
    public BaseScreen getScreen() {
        return (BaseScreen) screen;
    }

    @Override
    public void setScreen(Screen screen) {
        if (getScreen() != null) getScreen().dispose();
        super.setScreen(screen);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        gameData.profile.save();
        super.pause();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    public FitViewport getViewport() {
        return viewport;
    }

}
