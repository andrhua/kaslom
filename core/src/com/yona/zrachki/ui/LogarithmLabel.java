package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.yona.zrachki.core.GameData;
import com.yona.zrachki.game.Formula;

public class LogarithmLabel {
    private HorizontalGroup group;

    public HorizontalGroup getGroup() {
        return group;
    }

    public LogarithmLabel(Formula formula, GameData data) {
        Label logLabel = new Label("log", data.assets.logLabelStyle);
        Label baseLabel = new Label(String.valueOf(formula.getA()), data.assets.scriptLabelStyle);
        Label sublogLabel = new Label(String.valueOf(formula.getB()).concat(" = ").concat(String.valueOf(formula.getAnswer())), data.assets.logLabelStyle);
        group=new HorizontalGroup();
        group.addActor(logLabel);
        group.rowAlign(Align.bottom).addActor(baseLabel);
        group.addActor(sublogLabel);
        group.setBounds(logLabel.getX(), logLabel.getY(),
                baseLabel.getWidth()+logLabel.getWidth()+sublogLabel.getWidth(), sublogLabel.getHeight());
        group.setOrigin(group.getWidth()/2, group.getHeight()/2);

    }

}
