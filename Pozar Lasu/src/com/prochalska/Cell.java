package com.prochalska;

import java.util.Random;

enum State{
    ALIVE,EMPTY,FIRE,WATER;
}
public class Cell {
    State state;
    static Random random = new Random();
    double hum;
    double ter;
    Cell(State state,double h, double t){
        this.state = state;
        this.hum = h;
        this.ter = t;
    }
    Cell step(double wind, int burning, int alive){
        State newState = state;
        if(state == State.FIRE){
            newState = State.EMPTY;
        }
        else if( state == State.WATER){
            newState = State.WATER;
        }
        else if(state == State.ALIVE){
            if(burning*wind > random.nextDouble()*4){
                newState = State.FIRE;
            }
            else if((1-hum)*0.0001 >random.nextDouble()){
                newState = State.FIRE;
            }
        }
        else if(state == State.EMPTY ){
            if(alive*hum*0.1>random.nextDouble()){
                newState = State.ALIVE;
            }
        }
        return new Cell(newState, hum,ter);
    }
}
