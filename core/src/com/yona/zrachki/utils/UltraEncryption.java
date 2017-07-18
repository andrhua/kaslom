package com.yona.zrachki.utils;

import com.badlogic.gdx.utils.StringBuilder;

public class UltraEncryption {

    public static String encrypt(String unencryptedString){
        StringBuilder sb=new StringBuilder(unencryptedString);
        for (int i=0; i<sb.length(); i++) sb.setCharAt(i, primaryEncrypt(sb.charAt(i)));
        for (int i=0; i<sb.length(); i++) sb.setCharAt(i, secondaryEncrypt(sb.charAt(i)));
        return sb.toString();
    }

    //keyboard dependency
    private static char primaryEncrypt(char character){
        char tmp=character;
        switch (character){
            case '1':tmp='!'; break;
            case '2':tmp='@'; break;
            case '3':tmp='#'; break;
            case '4':tmp='$'; break;
            case '5':tmp='%'; break;
            case '6':tmp='^'; break;
            case '7':tmp='&'; break;
            case '8':tmp='*'; break;
            case '9':tmp='('; break;
            case '0':tmp=')'; break;
            case 'a':tmp='ф'; break;
            case 'b':tmp='и'; break;
            case 'c':tmp='с'; break;
            case 'd':tmp='в'; break;
            case 'e':tmp='у'; break;
            case 'f':tmp='а'; break;
            case 'g':tmp='п'; break;
            case 'h':tmp='р'; break;
            case 'i':tmp='ш'; break;
            case 'j':tmp='о'; break;
            case 'k':tmp='л'; break;
            case 'l':tmp='д'; break;
            case 'm':tmp='ь'; break;
            case 'n':tmp='т'; break;
            case 'o':tmp='щ'; break;
            case 'p':tmp='з'; break;
            case 'q':tmp='й'; break;
            case 'r':tmp='к'; break;
            case 's':tmp='ы'; break;
            case 't':tmp='е'; break;
            case 'u':tmp='г'; break;
            case 'v':tmp='м'; break;
            case 'w':tmp='ц'; break;
            case 'x':tmp='ч'; break;
            case 'y':tmp='н'; break;
            case 'z':tmp='я'; break;
            case 'а':tmp='f'; break;
            case 'б':tmp=','; break;
            case 'в':tmp='d'; break;
            case 'г':tmp='u'; break;
            case 'д':tmp='l'; break;
            case 'е':
            case 'ё':tmp='t'; break;
            case 'ж':tmp=';'; break;
            case 'з':tmp='p'; break;
            case 'и':tmp='b'; break;
            case 'й':tmp='q'; break;
            case 'к':tmp='r'; break;
            case 'л':tmp='k'; break;
            case 'м':tmp='v'; break;
            case 'н':tmp='y'; break;
            case 'о':tmp='j'; break;
            case 'п':tmp='g'; break;
            case 'р':tmp='h'; break;
            case 'с':tmp='c'; break;
            case 'т':tmp='n'; break;
            case 'у':tmp='e'; break;
            case 'ф':tmp='a'; break;
            case 'х':tmp='['; break;
            case 'ц':tmp='w'; break;
            case 'ч':tmp='x'; break;
            case 'ш':tmp='i'; break;
            case 'щ':tmp='o'; break;
            case 'ъ':tmp=']'; break;
            case 'ы':tmp='s'; break;
            case 'ь':tmp='m'; break;
            case 'э':tmp='\''; break;
            case 'ю':tmp='.'; break;
            case 'я':tmp='z'; break;
        }
        return tmp;
    }

    //similarity of phonemes
    private static char secondaryEncrypt(char character){
        char tmp=character;
        switch (character){
            case 'a':tmp='а'; break;
            case 'b':tmp='б'; break;
            case 'c':tmp='ц'; break;
            case 'd':tmp='д'; break;
            case 'e':tmp='е'; break;
            case 'f':tmp='ф'; break;
            case 'g':tmp='г'; break;
            case 'h':tmp='х'; break;
            case 'i':tmp='и'; break;
            case 'j':tmp='ж'; break;
            case 'k':tmp='л'; break;
            case 'l':tmp='л'; break;
            case 'm':tmp='м'; break;
            case 'n':tmp='н'; break;
            case 'o':tmp='о'; break;
            case 'p':tmp='п'; break;
            case 'r':tmp='р'; break;
            case 's':tmp='с'; break;
            case 't':tmp='т'; break;
            case 'u':tmp='ю'; break;
            case 'v':tmp='в'; break;
            case 'w':tmp='ш'; break;
            case 'y':tmp='у'; break;
            case 'z':tmp='з'; break;
            case 'а':tmp='a'; break;
            case 'б':tmp='b'; break;
            case 'в':tmp='v'; break;
            case 'г':tmp='g'; break;
            case 'д':tmp='d'; break;
            case 'е':tmp='e'; break;
            case 'ё':tmp='e'; break;
            case 'ж':tmp='j'; break;
            case 'з':tmp='z'; break;
            case 'и':tmp='i'; break;
            case 'к':tmp='k'; break;
            case 'л':tmp='l'; break;
            case 'м':tmp='m'; break;
            case 'н':tmp='n'; break;
            case 'о':tmp='o'; break;
            case 'п':tmp='p'; break;
            case 'р':tmp='r'; break;
            case 'с':tmp='s'; break;
            case 'т':tmp='t'; break;
            case 'у':tmp='y'; break;
            case 'ф':tmp='f'; break;
            case 'х':tmp='h'; break;
            case 'ю':tmp='u'; break;
        }
        return tmp;
    }
}
