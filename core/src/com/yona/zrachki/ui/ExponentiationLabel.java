package com.yona.zrachki.ui;


import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.assets.Styles;

import com.yona.zrachki.game.Formula;

public class ExponentiationLabel {
    private HorizontalGroup group;

    public ExponentiationLabel(Formula formula){
        Label baseLabel=new Label(String.valueOf(formula.getA()), Styles.formulaLabelStyle);
        baseLabel.setAlignment(Align.bottom);
        Label expLabel=new Label(String.valueOf(formula.getB()), Styles.scriptLabelStyle);
        Label answerLabel=new Label(" = ".concat(String.valueOf(formula.getAnswer())), Styles.formulaLabelStyle);
        group=new HorizontalGroup();
        group.addActor(baseLabel);
        group.rowAlign(Align.top).addActor(expLabel);
        group.addActor(answerLabel);
        group.setBounds(baseLabel.getX(), expLabel.getY(),
                baseLabel.getWidth()+expLabel.getWidth()+answerLabel.getWidth(), baseLabel.getHeight());
        group.setOrigin(group.getWidth()/2, group.getHeight()/2);

    }

    public HorizontalGroup getGroup() {
        return group;
    }

}
