package com.dietzmoss.robots.support;

/**
 * Heading helpers for wall travel. Robocode headings are degrees clockwise from north.
 */
public final class Headings
{
    private Headings()
    {
    }

    /**
     * Degrees to turn left so the heading lands on the nearest axis (0, 90, 180, or 270).
     *
     * @param heading current heading in degrees
     * @return a left turn in the range {@code [0, 90)}
     */
    public static double leftToNearestAxis(double heading)
    {
        return heading % 90.0;
    }
}
