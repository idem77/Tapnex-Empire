#!/bin/sh
# Gradle start script

DIR="$(cd "$(dirname "$0")" && pwd)"
exec "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"