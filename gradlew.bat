@echo off
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
"%JAVA_EXE%" -Xmx64m -Xms64m -classpath "gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
