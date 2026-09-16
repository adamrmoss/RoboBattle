package com.dietzmoss.simulations.support;

import java.io.File;

/**
 * Points the Robocode engine at the home Maven laid out under {@code target/robocode-home}.
 */
public final class RobocodeWorkspace
{
    private RobocodeWorkspace()
    {
    }

    /**
     * Set Robocode system properties for the packaged home directory.
     *
     * @return {@code simulations/target/robocode-home}
     */
    public static File prepare()
    {
        var home = new File("target/robocode-home");
        var robotsDir = new File(home, "robots");

        if (!robotsDir.isDirectory())
        {
            throw new IllegalStateException("Missing " + robotsDir.getAbsolutePath() + ". Run robo-battle so Maven copies the robots jar.");
        }

        System.setProperty("ROBOTPATH", robotsDir.getAbsolutePath());
        System.setProperty("WORKINGDIRECTORY", home.getAbsolutePath());

        return home;
    }
}
