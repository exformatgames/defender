package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.Gdx;

public class Debug {
    public static void log(String message){

        switch (Gdx.app.getType()) {

            case Desktop: {
                Gdx.app.log("Log", message);
                break;
            }

            case Android: {
                Gdx.app.log("log", message);
                break;
            }
        }
    }
}
