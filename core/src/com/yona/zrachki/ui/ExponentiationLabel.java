package com.yona.zrachki.ui;


import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.core.GameData;
import com.yona.zrachki.game.Formula;

public class ExponentiationLabel {
    private HorizontalGroup group;

    public ExponentiationLabel(Formula formula, GameData data){
        Label baseLabel=new Label(String.valueOf(formula.getA()), data.assets.logLabelStyle);
        baseLabel.setAlignment(Align.bottom);
        Label expLabel=new Label(String.valueOf(formula.getB()), data.assets.scriptLabelStyle);
        Label answerLabel=new Label(" = ".concat(String.valueOf(formula.getAnswer())), data.assets.logLabelStyle);
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
