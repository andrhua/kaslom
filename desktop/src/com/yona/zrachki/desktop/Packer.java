package com.yona.zrachki.desktop;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
    public static void main (String[] args) throws Exception {
        TexturePacker.Settings settings=new TexturePacker.Settings();
        settings.maxWidth=2048;
        settings.maxHeight=1024;
        TexturePacker.process(settings, "in", "out", "ui");
    }
}
