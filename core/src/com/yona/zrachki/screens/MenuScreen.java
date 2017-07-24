package com.yona.zrachki.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.assets.Styles;
import com.yona.zrachki.core.GameData;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.yona.zrachki.core.Constants.HEIGHT;
import static com.yona.zrachki.core.Constants.IMAGEBUTTON_SIZE;
import static com.yona.zrachki.core.Constants.MENU_SLIDE_DURATION;
import static com.yona.zrachki.core.Constants.WIDTH;
import static com.yona.zrachki.ui.UIHelper.initImageButton;
import static com.yona.zrachki.ui.UIHelper.initTextButton;

class MenuScreen extends BaseScreen {
    private Table romaTable, modeTable;
    private ImageButton backButton, exitButton;
    private float exitButtonPrimaryY, romaLabelPrimaryX;
    private Label romaLabel, modeLabel;
    public enum State {ROMA, MODE}
    private State state;

    MenuScreen(MyGame screenManager, GameData data) {
        super(screenManager, data);
    }

    public void setState(State state) {
        this.state = state;
        switch (state){
            case ROMA: {
                romaLabel.setVisible(true);
                backButton.addAction(moveTo(exitButton.getX(), -HEIGHT/10, MENU_SLIDE_DURATION, Interpolation.exp5));
                modeLabel.addAction(moveTo(-WIDTH/2, romaLabel.getY(), MENU_SLIDE_DURATION, Interpolation.exp5));
                modeTable.addAction(sequence(
                        moveTo(WIDTH, modeTable.getY(), MENU_SLIDE_DURATION, Interpolation.exp5),
                        run(new Runnable() {
                            @Override
                            public void run() {
                                exitButton.addAction(moveTo(backButton.getX(), exitButtonPrimaryY, MENU_SLIDE_DURATION, Interpolation.exp5Out));
                                romaTable.addAction(moveTo(WIDTH/2, romaTable.getY(), MENU_SLIDE_DURATION, Interpolation.exp5Out));
                                romaLabel.addAction(moveTo(romaLabelPrimaryX, romaLabel.getY(), MENU_SLIDE_DURATION, Interpolation.exp5Out));
                                modeLabel.setVisible(false);
                            }
                        })
                        )
                );
            }break;
            case MODE: {
                romaTable.addAction(moveTo(WIDTH, romaTable.getY(), MENU_SLIDE_DURATION, Interpolation.exp5));
                romaLabel.addAction(moveTo(-WIDTH/2, romaLabel.getY(), MENU_SLIDE_DURATION, Interpolation.exp5));
                exitButton.addAction(sequence(
                        moveTo(exitButton.getX(), -HEIGHT/10, MENU_SLIDE_DURATION, Interpolation.exp5),
                        run(new Runnable() {
                            @Override
                            public void run() {
                                romaLabel.setVisible(false);
                                stage.addActor(modeTable);
                                modeTable.setPosition(romaTable.getX(), romaTable.getY()+romaTable.getHeight()/2);
                                modeTable.addAction(moveTo(WIDTH/2, modeTable.getY(), MENU_SLIDE_DURATION, Interpolation.exp5Out));
                                stage.addActor(backButton);
                                backButton.setPosition(exitButton.getX(), exitButton.getY());
                                backButton.addAction(moveTo(backButton.getX(), exitButtonPrimaryY, MENU_SLIDE_DURATION, Interpolation.exp5Out));
                                stage.addActor(modeLabel);
                                modeLabel.setPosition(romaLabel.getX(), romaLabel.getY());
                                modeLabel.setVisible(true);
                                modeLabel.addAction(moveTo(WIDTH/4-modeLabel.getWidth()/2, modeLabel.getY(), MENU_SLIDE_DURATION, Interpolation.exp5Out));
                            }
                        })
                        )
                );
            }break;
        }
    }

    @Override
    public void createStage() {
        createRomaStage();
        createModeStage();
    }

    private void createRomaStage(){
        romaLabel =new Label("r o m a", Styles.titleLabelStyle);
        romaLabel.setAlignment(Align.center);

        TextButton settingsButton = initTextButton("settings");
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new SettingsScreen(screenManager, data));
            }
        });
        settingsButton.pad(10);

        exitButton = initImageButton("cancel");
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        TextButton playButton = initTextButton("play");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setState(State.MODE);
            }
        });
        playButton.pad(10);

        romaTable =new Table();
        romaTable.defaults().left();
        romaTable.add(playButton).row();
        romaTable.add(settingsButton);

        table.defaults().width(WIDTH/2);
        table.add(romaLabel).expandY();
        table.add(romaTable).row();
        table.add(exitButton).width(IMAGEBUTTON_SIZE).colspan(2);

        exitButtonPrimaryY=exitButton.getY()+table.getPadBottom();
        romaLabelPrimaryX = romaLabel.getX();
    }

    private void createModeStage(){
        modeLabel=new Label(data.i18n.getBundle().get("mode"), Styles.titleLabelStyle);
        modeLabel.setAlignment(Align.center);

        TextButton timeTrialButton=initTextButton("time_trial");
        timeTrialButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new GameScreen(screenManager, data, GameScreen.Mode.TIME_TRIAL));
            }
        });
        timeTrialButton.pad(10);

        TextButton rushButton=initTextButton("rush");
        rushButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new GameScreen(screenManager, data, GameScreen.Mode.RUSH));
            }
        });
        rushButton.pad(10);

        TextButton endlessButton=initTextButton("endless");
        endlessButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new GameScreen(screenManager, data, GameScreen.Mode.ENDLESS));
            }
        });
        endlessButton.pad(10);

        backButton=initImageButton("back");
        backButton.setWidth(64);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setState(State.ROMA);
            }
        });

        modeTable=new Table();
        modeTable.setWidth(WIDTH/2);
        modeTable.defaults().left();
        modeTable.add(timeTrialButton).row();
        modeTable.add(rushButton).row();
        modeTable.add(endlessButton);
    }

    @Override
    public void render() {

    }
}
