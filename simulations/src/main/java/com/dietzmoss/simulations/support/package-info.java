/**
 * Shared launch support for simulation apps (Robocode home, robot classpath).
 * <p>
 * Decision: copy {@code robots/target/robots-1.0-SNAPSHOT.jar} into
 * {@code home/robots}. That is the folder Robocode scans for packaged robots.
 * JDK 21 needs {@code --add-opens java.base/sun.net.www.protocol.jar=ALL-UNNAMED}
 * or the jar scan NPEs in {@code URLJarCollector}.
 */
package com.dietzmoss.simulations.support;
