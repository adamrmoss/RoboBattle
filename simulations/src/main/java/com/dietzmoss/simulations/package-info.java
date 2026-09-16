/**
 * Runnable Robocode simulations. Each subpackage is one app with a {@code main} method.
 * <p>
 * Decision: start a battle from the IDE Run configuration of the same name, or
 * {@code robo-battle <simulation>} at the repo root. That script sets {@code JAVA_HOME}
 * to JDK 21 and activates the Maven profile of the same name. {@code source}/{@code target}
 * 21 in the POM is compile only and does not choose the runtime. The forked process
 * is {@code ${java.home}/bin/java} with {@code -Djava.security.manager=allow} and
 * {@code --add-opens} so Robocode can scan the robots jar on JDK 21.
 * Add a matching Run configuration and Maven profile whenever a simulation is added.
 * <p>
 * Agents come from the {@code robots} module. This module only prepares a Robocode
 * home, picks participants, and shows the battle window.
 */
package com.dietzmoss.simulations;
