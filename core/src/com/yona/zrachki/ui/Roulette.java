package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Array;
import com.yona.zrachki.assets.Styles;
import com.yona.zrachki.core.Constants;

import com.yona.zrachki.game.Formula;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.yona.zrachki.core.Constants.ROULETTE_SCALE_STEP;

public class Roulette {
    private Stage stage;
    private VerticalGroup group;
    private Array<Formula>formulas;
    private Array<WidgetGroup> labels;
    private int activeIndex, numOfFormulas;
    private float CELL_HEIGHT;
    private Action inAnimation, outAnimation;

    public Roulette(int numOffFormulas, Stage stage){
        this.numOfFormulas=numOffFormulas;
        this.stage=stage;
        group=new VerticalGroup();
        group.reverse();
        formulas=new Array<Formula>();
        labels=new Array<WidgetGroup>();
        Formula.Generator generator=new Formula.Generator();
        for (int i=0; i<numOffFormulas; i++) {
            Formula f=generator.generate();
            formulas.add(f);
            labels.add(generateLabel(f));
        }
        initFirstFour();
        CELL_HEIGHT=-labels.get(0).getHeight();
        inAnimation =parallel(moveBy(0, CELL_HEIGHT, .6f), scaleBy(ROULETTE_SCALE_STEP, ROULETTE_SCALE_STEP, .6f));
        outAnimation=parallel(moveBy(0, CELL_HEIGHT, .6f), scaleBy(-ROULETTE_SCALE_STEP, -ROULETTE_SCALE_STEP, .6f));
    }

    private WidgetGroup generateLabel(Formula formula){
        WidgetGroup wg=null;
        switch (formula.getOperation()){
            case ADD: case SUBSTRACT: case MULTIPLY: case DIVIDE:
                Label l=new Label(formula.toString(), Styles.formulaLabelStyle);
                Container<Label> c=new Container<Label>();
                c.setTransform(true);
                c.setBounds(l.getX(), l.getY(), l.getWidth(), l.getHeight());
                c.setActor(l);
                c.setOrigin(l.getWidth()/2, l.getHeight()/2);
                wg=c;
                break;
            case EXP: wg=new ExponentiationLabel(formula).getGroup(); break;
            case LOG: wg=new com.yona.zrachki.ui.LogarithmLabel(formula).getGroup(); break;
        }
        return wg;
    }

    private void initFirstFour(){
        activeIndex =0;
        for (int i=0; i<4; i++) {
            WidgetGroup wg=labels.get(i);
            //group.addActor(wg);
            stage.addActor(wg);
            wg.setPosition(Constants.WIDTH/2-wg.getWidth()/2, Constants.HEIGHT/2-wg.getHeight()/2+i*Constants.HEIGHT/10);
            wg.setOrigin(wg.getWidth()/2, wg.getHeight()/2);
            wg.setScale(1-ROULETTE_SCALE_STEP*i);
        }
    }

    //анимировать, удалить, вставить, скейлнуть

    private void switchToNext(){
        if (numOfFormulas-activeIndex>3) {
            WidgetGroup wg=labels.get(activeIndex+4);
            stage.addActor(wg);
            wg.setScale(.25f);
            labels.get(activeIndex+4).addAction(moveBy(0, CELL_HEIGHT, .6f)); //inv
        }
        if (numOfFormulas-activeIndex>2){
            labels.get(activeIndex+3).addAction(inAnimation);
        }
        if (numOfFormulas-activeIndex>1){
            labels.get(activeIndex+2).addAction(inAnimation);
        }
        if (numOfFormulas>activeIndex){
            labels.get(activeIndex+1).addAction(inAnimation);
        }
        labels.get(activeIndex).addAction(outAnimation);
        if (activeIndex>0){
            labels.get(activeIndex-1).addAction(outAnimation);
        }
        if (activeIndex>1) {
            labels.get(activeIndex-2).addAction(sequence(
                    Actions.moveBy(0, CELL_HEIGHT, .6f),
                    run(new Runnable() {
                        @Override
                        public void run() {
                            //group.removeActor(labels.get(activeIndex-2));
                            labels.get(activeIndex-2).setVisible(false);
                        }
                    })
            ));
        }
        activeIndex++;
    }

    public boolean isRightAnswer(boolean answer){
        switchToNext();
        return answer==formulas.get(activeIndex).isRight();
    }

    public VerticalGroup getGroup() {
        return group;
    }

}
