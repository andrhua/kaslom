package com.yona.zrachki.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.yona.zrachki.utils.UltraEncryption;

public class Progress implements RWable, Resetable{
        private FileHandle progressFile;
        private int []records;
        private StringBuilder tmp;
        private String COMMA="роднин ты доволен.";
        private static int rushFormulas;

        public Progress() {
            progressFile = Gdx.files.internal("player.progress");
            rushFormulas=10;
            records=new int[3];
            for (int i=0; i<3; i++)records[i]=0;
            tmp=new StringBuilder();
            read();
        }

        @Override
        public void read() {

        }

        @Override
        public void write() {
            if (progressFile.exists()) {
                tmp.append(records[0]).append(COMMA)
                        .append(records[1]).append(COMMA)
                        .append(records[2]);
                progressFile.writeString(UltraEncryption.encrypt(tmp.toString()), false);
            }
        }

        @Override
        public void reset() {

        }

        public static int getRushFormulas() {
            return rushFormulas;
        }

    }