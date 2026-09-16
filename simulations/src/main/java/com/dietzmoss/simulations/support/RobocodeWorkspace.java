package com.dietzmoss.simulations.support;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Prepares a Robocode working directory that can see our {@code robots} module.
 */
public final class RobocodeWorkspace
{
    private static final String ROBOTS_JAR_NAME = "robots-1.0-SNAPSHOT.jar";

    private RobocodeWorkspace()
    {
    }

    /**
     * Create {@code target/robocode-home} and copy the robots module jar into {@code robots/}.
     *
     * @param robotType a robot class from the {@code robots} module
     * @return the home directory to pass to {@code new RobocodeEngine(home)}
     */
    public static File prepare(Class<?> robotType)
    {
        var home = new File("target/robocode-home");
        var robotsDir = new File(home, "robots");

        // Create the folders Robocode expects under its home.
        if (!robotsDir.mkdirs() && !robotsDir.isDirectory())
        {
            throw new IllegalStateException("Could not create " + robotsDir.getAbsolutePath());
        }

        // Drop stale jars and the robot database so this launch sees a fresh copy.
        removeStaleRobotFiles(robotsDir);

        var jar = robotJar(robotType);

        copyJar(jar, new File(robotsDir, jar.getName()));

        System.setProperty("ROBOTPATH", robotsDir.getAbsolutePath());
        System.setProperty("WORKINGDIRECTORY", home.getAbsolutePath());

        return home;
    }

    /**
     * Resolve the packaged robots jar (Maven {@code package} output).
     *
     * @param robotType a robot class from the {@code robots} module
     * @return {@code robots/target/robots-1.0-SNAPSHOT.jar}
     */
    private static File robotJar(Class<?> robotType)
    {
        var compiled = compiledLocation(robotType);

        if (compiled.isFile() && compiled.getName().endsWith(".jar"))
        {
            return compiled;
        }

        var packaged = new File(compiled.getParentFile(), ROBOTS_JAR_NAME);

        if (packaged.isFile())
        {
            return packaged;
        }

        throw new IllegalStateException("Robots jar not found next to " + compiled.getAbsolutePath());
    }

    /**
     * Resolve the jar or classes directory that contains the robot type.
     *
     * @param robotType a robot class from the {@code robots} module
     * @return a directory of class files, or a jar file
     */
    private static File compiledLocation(Class<?> robotType)
    {
        var url = robotType.getProtectionDomain().getCodeSource().getLocation();

        try
        {
            return new File(url.toURI());
        }
        catch (URISyntaxException ex)
        {
            return new File(url.getPath());
        }
    }

    /**
     * Copy the robots jar into the Robocode robots directory.
     *
     * @param source the robots module jar
     * @param destination the file under {@code robots/}
     */
    private static void copyJar(File source, File destination)
    {
        try
        {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex)
        {
            throw new UncheckedIOException("Could not copy robot jar into Robocode home", ex);
        }
    }

    /**
     * Delete leftover jars, zips, and Robocode's robot database from the robots folder.
     *
     * @param robotsDir {@code target/robocode-home/robots}
     */
    private static void removeStaleRobotFiles(File robotsDir)
    {
        var files = robotsDir.listFiles();

        if (files == null)
        {
            return;
        }

        for (var file : files)
        {
            var name = file.getName().toLowerCase();

            if (file.isFile() && (name.endsWith(".jar") || name.endsWith(".zip") || name.equals("robot.database")))
            {
                file.delete();
            }
        }
    }
}
