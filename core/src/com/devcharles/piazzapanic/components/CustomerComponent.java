package com.devcharles.piazzapanic.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.devcharles.piazzapanic.components.FoodComponent.FoodType;
import com.devcharles.piazzapanic.utility.GdxTimer;

public class CustomerComponent implements Component, Poolable {
    public FoodType order = null;
    public Entity interactingCook = null;
    public Entity food = null;
    public GdxTimer timer = new GdxTimer(120000, false, false);

    @Override
    public void reset() {
        order = null;
        interactingCook = null;
        food = null;
        timer.stop();
        timer.reset();
    }

    public void setTimer(int timeRemaining) {
        timer = new GdxTimer(timeRemaining, false, false);
    }
}
