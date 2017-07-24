package com.yona.zrachki.screens;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.MyGame;
import com.yona.zrachki.assets.Styles;
import com.yona.zrachki.core.Constants;
import com.yona.zrachki.core.GameData;
import com.yona.zrachki.ui.Roulette;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.yona.zrachki.core.Constants.IMAGEBUTTON_SIZE;
import static com.yona.zrachki.core.Constants.LAYERS_SWITCH_DURATION;
import static com.yona.zrachki.ui.UIHelper.initImageButton;
import static com.yona.zrachki.ui.UIHelper.initTextButton;


public class GameScreen extends BaseScreen {
    private Label scoreLabel, timerLabel, finishLabel, statsLabel, helpLabel, startLabel, modeLabel;
    private Roulette roulette;
    private ImageButton pauseButton, backButton, exitButton1, exitButton2, nextButton, yesButton, noButton;
    private TextButton restartButton1, restartButton2;
    private Dialog exitDialog, restartDialog;
    public enum Mode{TIME_TRIAL, RUSH, ENDLESS}
    private enum State{PREGAME, GAME, PAUSE, FINISH, STATS}
    private Mode mode;
    private State state;
    private Table pregameTable, gameTable, pauseTable, finishTable, statsTable;
    private int score, left, solved, numOfFormulas;
    private float time;

    GameScreen(MyGame screenManager, GameData data, Mode mode) {
        super(screenManager, data);
        setMode(mode);
        setState(State.PREGAME);
    }

    private void setMode(Mode mode){
        this.mode=mode;
        switch (mode){
            case TIME_TRIAL:{
                score=0;
                time=30;
                numOfFormulas=50;
            } break;
            case RUSH:{
                left=data.profile.progress.rushFormulas;
                numOfFormulas=left;
                time=0;
            } break;
            case ENDLESS:{
                numOfFormulas=50;
                solved=0;
                time=5;
            }
        }
    }

    private void setState(State state){
        this.state=state;
        switch (state){
            case PREGAME:break;
            case GAME:
                gameTable.addAction(moveTo(0, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                pauseTable.addAction(moveTo(-width, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                break;
            case PAUSE:
                gameTable.addAction(moveTo(width, 0, LAYERS_SWITCH_DURATION-.1f, Interpolation.exp5Out));
                pauseTable.addAction(moveTo(0, 0, LAYERS_SWITCH_DURATION-.1f, Interpolation.exp5Out));
                break;
            case FINISH:
                gameTable.addAction(moveTo(-width, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                finishTable.addAction(moveTo(0, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                break;
            case STATS:
                finishTable.addAction(moveTo(-width, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                statsTable.addAction(moveTo(0, 0, LAYERS_SWITCH_DURATION, Interpolation.exp5));
                break;
        }
    }

    @Override
    public void createStage() {
        stage.setDebugAll(true);
        stage.clear();
        createPregameStage();
        createGameStage();
        createPauseStage();
        createFinishStage();
        createStatsStage();

        stage.addActor(gameTable);
        gameTable.setPosition(0,0);
        stage.addActor(pauseTable);
        pauseTable.setPosition(-width,0);
        stage.addActor(finishTable);
        finishTable.setPosition(width, 0);
        stage.addActor(statsTable);
        statsTable.setPosition(2*width, 0);

        stage.addActor(pregameTable);
        pregameTable.setPosition(width/2-pregameTable.getWidth()/2, height/2-pregameTable.getHeight()/2);
        stage.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if (state==State.PREGAME) {
                    pregameTable.setVisible(false);
                    setState(State.GAME);
                }
            }
        });
    }

    private void createPregameStage(){
        helpLabel=new Label(data.i18n.getBundle().get("help_".concat(
                mode==Mode.TIME_TRIAL?"time_trial":mode==Mode.RUSH?"rush":"endless")), Styles.regularLabelStyle);
        helpLabel.setWrap(true);
        helpLabel.setAlignment(Align.center);
        startLabel=new Label(data.i18n.getBundle().get("tap_to_start"), Styles.regularLabelStyle);

        pregameTable=new Table();
        pregameTable.defaults().align(Align.center);
        pregameTable.add(helpLabel).width(width*.9f).row();
        pregameTable.add(startLabel);
    }

    private void createGameStage(){
        pauseButton=initImageButton("pause");
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setState(State.PAUSE);
            }
        });

        scoreLabel=new Label(data.i18n.getBundle().get(
                mode==Mode.TIME_TRIAL?"score":mode==Mode.RUSH?"left":"solved").concat(":"),
                Styles.regularLabelStyle);
        timerLabel=new Label(data.i18n.getBundle().get("time").concat(":"), Styles.regularLabelStyle);
        roulette=new Roulette(numOfFormulas, data, stage);

        yesButton=initImageButton("yes");
        yesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                submitAnswer(true);
            }
        });

        noButton=initImageButton("no");
        noButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                submitAnswer(false);
            }
        });

        gameTable=new Table();
        gameTable.setFillParent(true);
        gameTable.add(pauseButton).width(IMAGEBUTTON_SIZE);
        gameTable.add(scoreLabel).expandX();
        gameTable.add(timerLabel).expandX().row();
        gameTable.add(roulette.getGroup()).colspan(3).expandY().pad(50, 0 , 50, 0).row();
        Table answerTable=new Table();
        answerTable.add(yesButton).size(Constants.ANSWER_BUTTON_SIZE).expandX().padRight(25);
        answerTable.add(noButton).size(Constants.ANSWER_BUTTON_SIZE).expandX().padLeft(25);
        gameTable.add(answerTable).height(height/4).colspan(3);
    }

    private void createPauseStage(){
        exitButton1 =initImageButton("cancel");
        exitButton1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameTable.setVisible(false);
                finishTable.setVisible(false);
                setScreen(new MenuScreen(screenManager, data));
            }
        });
        restartButton1 =initTextButton("restart");
        restartButton1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                restartGame();
            }
        });
        backButton=initImageButton("back");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setState(State.GAME);
            }
        });

        modeLabel=new Label(data.i18n.getBundle().get(
                mode==Mode.TIME_TRIAL?"time_trial":mode==Mode.RUSH?"rush":"endless").concat(" mode"),
                Styles.regularLabelStyle);

        pauseTable=new Table();
        pauseTable.setFillParent(true);
        pauseTable.add(backButton).width(IMAGEBUTTON_SIZE).align(Align.left).row();
        pauseTable.add(modeLabel).row();
        pauseTable.add(restartButton1).expand().row();
        pauseTable.add(exitButton1).width(IMAGEBUTTON_SIZE);
    }

    private void createFinishStage(){
        finishLabel=new Label(data.i18n.getBundle().get("finish_text"), Styles.regularLabelStyle);
        nextButton=initImageButton("next");
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setState(State.STATS);
            }
        });

        finishTable=new Table();
        finishTable.setFillParent(true);
        finishTable.defaults().align(Align.center);
        finishTable.add(finishLabel).expand().row();
        finishTable.add(nextButton).width(IMAGEBUTTON_SIZE);

    }

    private void createStatsStage(){
        statsLabel=new Label("Statssssss", Styles.regularLabelStyle);
        exitButton2 =initImageButton("cancel");
        exitButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameTable.setVisible(false);
                finishTable.setVisible(false);
                pauseTable.setVisible(false);
                setScreen(new MenuScreen(screenManager, data));
            }
        });
        restartButton2 =initTextButton("restart");
        restartButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                restartGame();
            }
        });

        statsTable=new Table();
        statsTable.setFillParent(true);
        statsTable.add(statsLabel).row();
        statsTable.add(restartButton2).expand().row();
        statsTable.add(exitButton2).width(IMAGEBUTTON_SIZE);
    }

    private void submitAnswer(boolean answer){
        if (roulette.isRightAnswer(answer)) {} else {}
            //setState(State.FINISH);
    }

    private void restartGame(){};

    @Override
    public void render() {

    }

}
