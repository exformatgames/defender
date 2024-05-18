package com.github.exformatgames.defender.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.EndContactComponent;

public class B2DContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        EntityBuilder.addComponent((Entity) a.getUserData(), BeginContactComponent.class).init((Entity) b.getUserData(), b, contact, contact.getFixtureB().getFilterData().categoryBits);
        EntityBuilder.addComponent((Entity) b.getUserData(), BeginContactComponent.class).init((Entity) a.getUserData(), a, contact, contact.getFixtureA().getFilterData().categoryBits);
    }

    @Override
    public void endContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        EntityBuilder.addComponent((Entity) a.getUserData(), EndContactComponent.class).init((Entity) b.getUserData(), contact, contact.getFixtureB().getFilterData().categoryBits);
        EntityBuilder.addComponent((Entity) b.getUserData(), EndContactComponent.class).init((Entity) a.getUserData(), contact, contact.getFixtureA().getFilterData().categoryBits);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
