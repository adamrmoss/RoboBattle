package com.dietzmoss.robots.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks axis-align turns used by wall-following robots.
 */
public class HeadingsTest
{
    private static final double TOLERANCE = 0.0001;

    /**
     * A heading already on an axis needs no turn.
     */
    @Test
    public void leftToNearestAxis_whenHeadingIsZero_returnsZero()
    {
        assertEquals(0.0, Headings.leftToNearestAxis(0.0), TOLERANCE);
    }

    /**
     * 90 degrees is already on an axis.
     */
    @Test
    public void leftToNearestAxis_whenHeadingIs90_returnsZero()
    {
        assertEquals(0.0, Headings.leftToNearestAxis(90.0), TOLERANCE);
    }

    /**
     * Halfway between axes turns left 45 degrees.
     */
    @Test
    public void leftToNearestAxis_whenHeadingIs45_returns45()
    {
        assertEquals(45.0, Headings.leftToNearestAxis(45.0), TOLERANCE);
    }
}
