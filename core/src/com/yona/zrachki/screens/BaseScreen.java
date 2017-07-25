package com.yona.zrachki.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.core.Constants;
import com.yona.zrachki.gfx.GlWrap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public abstract class BaseScreen extends ScreenAdapter implements Stagable, Renderable {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;
    MyGame screenManager;
    int width, height;
    Stage stage;
    Table table;
    Color backgroundColor;

    BaseScreen(MyGame screenManager){
        this.screenManager = screenManager;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        width=Constants.WIDTH;
        height=Constants.HEIGHT;
        spriteBatch=new SpriteBatch();
        shapeRenderer=new ShapeRenderer();
        table=new Table();
        table.setFillParent(true);
        table.pad(5);
        table.setDebug(true);
        stage=new Stage(screenManager.getViewport());
        Gdx.input.setInputProcessor(stage);
        backgroundColor=Color.WHITE;
        enterAnimation();
        stage.addActor(table);
        createStage();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        GlWrap.drawBackgroundColor(backgroundColor);
        render();
        stage.act(delta);
        stage.draw();
    }

    void setScreen(final BaseScreen screen){
        stage.addAction(sequence(
                moveTo(Constants.WIDTH, 0, Constants.SCREEN_SWITCH_DURATION, Interpolation.exp5),
                run(new Runnable() {
                    @Override
                    public void run() {
                        screenManager.setScreen(screen);
                    }
                })
                )
        );
    }



    void enterAnimation(){
        stage.addAction(sequence(alpha(0), fadeIn(Constants.START_SCREEN_ANIMATION, Interpolation.exp5In)));
    }

}
