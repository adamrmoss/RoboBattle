/**
 * Shared launch support for simulation apps.
 * <p>
 * Decision: Maven copies {@code robots.jar} into
 * {@code target/robocode-home/robots} at {@code prepare-package}. This class only
 * points {@code RobocodeEngine} at that home. JDK 21 still needs {@code --add-opens}
 * for Robocode's jar scanner.
 */
package com.dietzmoss.simulations.support;
