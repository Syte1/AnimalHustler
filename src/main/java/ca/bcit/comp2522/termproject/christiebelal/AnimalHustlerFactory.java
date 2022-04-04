package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.AnimalComponent;
import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

/**
 * Handles the properties of game entities.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class AnimalHustlerFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder(data)
                .type(PLAYER)
                .with(physics)
                .bbox(new HitBox(new Point2D(22,32), BoundingShape.box(30, 30)))
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("cow")
    public Entity newCow(SpawnData data) {
        var hp = new HealthIntComponent(5);

        var hpView = new ProgressBar(false);
        hpView.setFill(Color.LIGHTGREEN);
        hpView.setMaxValue(5);
        hpView.setWidth(85);
        hpView.setTranslateY(-15);
        hpView.setTranslateX(-9);
        hpView.currentValueProperty().bind(hp.valueProperty());

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder(data)
                .type(COW)
                .with(physics)
                .bbox(new HitBox(new Point2D(-5,5), BoundingShape.box(76, 51)))
                .with(new CollidableComponent(true))
                .with(new AnimalComponent())
                .view(hpView)
                .with(hp)
                .build();
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data){
        return entityBuilder(data)
                .type(WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }


}
