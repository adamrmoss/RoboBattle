package com.dietzmoss.simulations.perimeter;

import com.dietzmoss.robots.PerimeterPatrol;
import com.dietzmoss.simulations.support.RobocodeWorkspace;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

/**
 * Opens the Robocode window and runs four perimeter-patrol robots for one round.
 */
public final class PerimeterPatrolSimulation
{
    private static final String ROBOT_NAME = "com.dietzmoss.robots.PerimeterPatrol";
    private static final int ROBOT_COUNT = 4;
    private static final int ROUNDS = 1;
    private static final int FIELD_WIDTH = 800;
    private static final int FIELD_HEIGHT = 600;

    private PerimeterPatrolSimulation()
    {
    }

    /**
     * Prepare a Robocode home, show the UI, and run the patrol battle.
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        var home = RobocodeWorkspace.prepare(PerimeterPatrol.class);
        var engine = new RobocodeEngine(home);

        // Show the battlefield so the class can watch the patrol.
        engine.setVisible(true);

        var patrol = findPatrol(engine.getLocalRepository());

        if (patrol == null)
        {
            engine.close();
            throw new IllegalStateException("PerimeterPatrol was not found in the Robocode robots folder");
        }

        var robots = new RobotSpecification[ROBOT_COUNT];

        // Repeat the same agent so several patrols share the walls.
        for (var i = 0; i < ROBOT_COUNT; i++)
        {
            robots[i] = patrol;
        }

        var battlefield = new BattlefieldSpecification(FIELD_WIDTH, FIELD_HEIGHT);
        var battle = new BattleSpecification(ROUNDS, battlefield, robots);

        // Block until the round ends so the window is not torn down immediately.
        engine.runBattle(battle, true);
        engine.close();
        System.exit(0);
    }

    /**
     * Find PerimeterPatrol in the repository, including a version suffix if Robocode added one.
     *
     * @param installed all robots Robocode discovered
     * @return the matching spec, or {@code null}
     */
    private static RobotSpecification findPatrol(RobotSpecification[] installed)
    {
        for (var spec : installed)
        {
            if (spec.getName().equals(ROBOT_NAME) || spec.getNameAndVersion().startsWith(ROBOT_NAME))
            {
                return spec;
            }
        }

        return null;
    }
}
