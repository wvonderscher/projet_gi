@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\mysql-connector-java-8.0.29.jar;"%REPO%"\protobuf-java-3.19.4.jar;"%REPO%"\appassembler-maven-plugin-1.10.jar;"%REPO%"\appassembler-model-1.10.jar;"%REPO%"\stax-utils-20060502.jar;"%REPO%"\stax-1.1.1-dev.jar;"%REPO%"\commons-io-1.4.jar;"%REPO%"\plexus-utils-3.0.20.jar;"%REPO%"\plexus-interpolation-1.21.jar;"%REPO%"\maven-mapping-1.0.jar;"%REPO%"\stax-api-1.0.1.jar;"%REPO%"\maven-model-2.2.1.jar;"%REPO%"\maven-plugin-api-2.2.1.jar;"%REPO%"\maven-project-2.2.1.jar;"%REPO%"\maven-settings-2.2.1.jar;"%REPO%"\maven-profile-2.2.1.jar;"%REPO%"\maven-artifact-manager-2.2.1.jar;"%REPO%"\maven-repository-metadata-2.2.1.jar;"%REPO%"\wagon-provider-api-1.0-beta-6.jar;"%REPO%"\backport-util-concurrent-3.1.jar;"%REPO%"\maven-plugin-registry-2.2.1.jar;"%REPO%"\plexus-container-default-1.0-alpha-9-stable-1.jar;"%REPO%"\classworlds-1.1-alpha-2.jar;"%REPO%"\maven-filtering-1.3.jar;"%REPO%"\maven-core-2.2.1.jar;"%REPO%"\wagon-file-1.0-beta-6.jar;"%REPO%"\maven-plugin-parameter-documenter-2.2.1.jar;"%REPO%"\wagon-http-lightweight-1.0-beta-6.jar;"%REPO%"\wagon-http-shared-1.0-beta-6.jar;"%REPO%"\xercesMinimal-1.9.6.2.jar;"%REPO%"\nekohtml-1.9.6.2.jar;"%REPO%"\wagon-http-1.0-beta-6.jar;"%REPO%"\wagon-webdav-jackrabbit-1.0-beta-6.jar;"%REPO%"\jackrabbit-webdav-1.5.0.jar;"%REPO%"\jackrabbit-jcr-commons-1.5.0.jar;"%REPO%"\commons-httpclient-3.0.jar;"%REPO%"\commons-codec-1.2.jar;"%REPO%"\slf4j-nop-1.5.3.jar;"%REPO%"\slf4j-jdk14-1.5.6.jar;"%REPO%"\slf4j-api-1.5.6.jar;"%REPO%"\jcl-over-slf4j-1.5.6.jar;"%REPO%"\maven-reporting-api-2.2.1.jar;"%REPO%"\doxia-sink-api-1.1.jar;"%REPO%"\doxia-logging-api-1.1.jar;"%REPO%"\maven-error-diagnostics-2.2.1.jar;"%REPO%"\commons-cli-1.2.jar;"%REPO%"\wagon-ssh-external-1.0-beta-6.jar;"%REPO%"\wagon-ssh-common-1.0-beta-6.jar;"%REPO%"\maven-plugin-descriptor-2.2.1.jar;"%REPO%"\plexus-interactivity-api-1.0-alpha-4.jar;"%REPO%"\maven-monitor-2.2.1.jar;"%REPO%"\wagon-ssh-1.0-beta-6.jar;"%REPO%"\jsch-0.1.38.jar;"%REPO%"\plexus-sec-dispatcher-1.3.jar;"%REPO%"\plexus-cipher-1.4.jar;"%REPO%"\maven-shared-utils-0.6.jar;"%REPO%"\jsr305-2.0.1.jar;"%REPO%"\plexus-build-api-0.0.4.jar;"%REPO%"\maven-artifact-2.2.1.jar;"%REPO%"\plexus-archiver-2.7.1.jar;"%REPO%"\plexus-io-2.2.jar;"%REPO%"\commons-compress-1.9.jar;"%REPO%"\projet_gi-0.0.1-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% --module-path %JAVAFX_HOME%\lib --add-modules javafx.controls,javafx.fxml -classpath %CLASSPATH% -Dapp.name="run" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" fr.ul.miage.projet_gi.Launcher %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
