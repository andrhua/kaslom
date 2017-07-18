package com.yona.zrachki.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

public class MyCheckBox {
    private Table table;
    private boolean isChecked;
    private CheckBox.CheckBoxStyle cbs;
    private Image image;

    public MyCheckBox(String text, Skin skin){
        isChecked=false;
        cbs= skin.get("default", CheckBox.CheckBoxStyle.class);
        Label label=new Label(text, new Label.LabelStyle(cbs.font, cbs.fontColor));
        image=new Image(cbs.checkboxOff);
        image.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                isChecked=!isChecked;
                image.setDrawable(isChecked?cbs.checkboxOn:cbs.checkboxOff);
            }
        });
        table=new Table();
        table.add(image).align(Align.center).size(65);
        table.add(label);
    }

    public Table getTable() {
        return table;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        image.setDrawable(isChecked?cbs.checkboxOn:cbs.checkboxOff);
    }

    public Cell getLabelCell(){
        return table.getCells().get(0);
    }

    public Cell getImageCell(){
        return table.getCells().get(1);
    }


}
