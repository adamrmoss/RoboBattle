package com.dietzmoss.robots;

import com.dietzmoss.robots.support.Headings;
import robocode.HitRobotEvent;
import robocode.Robot;

/**
 * Drive the rectangle of the battlefield: align to an axis, go forward, turn at each corner.
 */
public class PerimeterPatrol extends Robot
{
    /**
     * Align to a wall direction, then loop: travel the field length and turn right 90 degrees.
     */
    @Override
    public void run()
    {
        var travel = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

        // Face north, east, south, or west so the path follows the walls.
        turnLeft(Headings.leftToNearestAxis(getHeading()));

        while (true)
        {
            // Drive far enough to reach the next corner.
            ahead(travel);

            // Turn to follow the next wall (clockwise).
            turnRight(90);
        }
    }

    /**
     * Back off when we bump another robot so the patrol can continue.
     *
     * @param event the collision from Robocode
     */
    @Override
    public void onHitRobot(HitRobotEvent event)
    {
        // Move away from the other robot and resume the wall path.
        back(50);
    }
}
