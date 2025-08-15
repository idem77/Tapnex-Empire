#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Check if JAVA_HOME is set
if [ -z "$JAVA_HOME" ]; then
  echo "ERROR: JAVA_HOME is not set!"
  exit 1
fi

exec "$JAVA_HOME/bin/java" -Xmx64m -Xms64m -classpath "gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
