package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.assets.Styles;

import com.yona.zrachki.game.Formula;

public class LogarithmLabel {
    private HorizontalGroup group;

    public HorizontalGroup getGroup() {
        return group;
    }

    public LogarithmLabel(Formula formula) {
        Label logLabel = new Label("log", Styles.formulaLabelStyle);
        Label baseLabel = new Label(String.valueOf(formula.getA()), Styles.scriptLabelStyle);
        Label sublogLabel = new Label(String.valueOf(formula.getB()).concat(" = ").concat(String.valueOf(formula.getAnswer())), Styles.formulaLabelStyle);
        group=new HorizontalGroup();
        group.addActor(logLabel);
        group.rowAlign(Align.bottom).addActor(baseLabel);
        group.addActor(sublogLabel);
        group.setBounds(logLabel.getX(), logLabel.getY(),
                baseLabel.getWidth()+logLabel.getWidth()+sublogLabel.getWidth(), sublogLabel.getHeight());
        group.setOrigin(group.getWidth()/2, group.getHeight()/2);

    }

}
