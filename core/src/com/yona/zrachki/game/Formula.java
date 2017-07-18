package com.yona.zrachki.game;

import com.badlogic.gdx.math.MathUtils;

import java.security.SecureRandom;

public class Formula {
    private int a, b, result;
    private boolean isRight;
    public enum Operation{ADD, SUBSTRACT, MULTIPLY, DIVIDE, EXP, LOG}
    private Operation operation;

    public Formula(int a, int b, int result, boolean isRight, Operation operation) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.isRight = isRight;
        this.operation = operation;
    }

    @Override
    public String toString() {
        if (operation==Operation.EXP||operation==Operation.LOG) return "pidroller";
        return String.valueOf(a)
                .concat(operation==Operation.ADD?" + ":
                        operation==Operation.SUBSTRACT?" - ":
                                operation==Operation.MULTIPLY?" * ":" : ")
                .concat(String.valueOf(b)).concat(" = ").concat(String.valueOf(result));

    }

    public Operation getOperation() {
        return operation;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getAnswer() { return result; }

    public boolean isRight() {
        return isRight;
    }

    public static class Generator {
        private SecureRandom secureRandom;

        public Generator(){
            secureRandom=new SecureRandom();
        }

        public Formula generate(){
            Operation operation=getOperation(secureRandom.nextInt(6));
            int[] ab=generateAB(operation);
            int ans=calcAnswer(ab[0], ab[1], operation);
            boolean isRight=secureRandom.nextBoolean();
            Formula formula=new Formula(ab[0], ab[1], ans, isRight, operation);
            if (isRight)return formula; else return generateError(formula);
        }

        private int calcAnswer(int a, int b, Operation operation){
            int r;
            switch (operation){
                case ADD: r=a+b; break;
                case SUBSTRACT: r=a-b; break;
                case MULTIPLY: r=a*b; break;
                case DIVIDE: r=a/b; break;
                case EXP: r=(int)Math.pow(a, b); break;
                default: r= (int)MathUtils.log(a, b); break;
            }
            return r;
        }

        private int[]generateAB(Operation operation){
            int a=secureRandom.nextInt(100)+1, b=secureRandom.nextInt(100)+1;
            return new int[]{a, b};
        }

        private Operation getOperation(int operation){
            return Operation.values()[operation];
        }

        private Formula generateError(Formula formula){
            formula.b-=1;
            return formula;
        }

    }

}
