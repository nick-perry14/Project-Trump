# Building with Eclipse
## Cloning The Repository
* Either clone the repository, or download and import.
## Run Configration
1. Click the arrow next to the run button with the red tool box
2. Select "External Tool Configurations"
3. Right click on the menu, and select "New Configuration"
4. Select (if prompted) to ANT BUILD
5. Click on the new configuration, and under the Main tab, go to build file
6. Select "Browse Workspace"
7. Find and select build.xml
8. Select "Targets" Tab
9. Check compile, jar, run
10. Click on the "order" button at the bottom of the screen
11. Change the order so it is as follows: compile, jar, run
12. Select "OK"
13. Select "Apply"
14. Run new configuration, application should launch.
15. Jar file located at: /build/jar/ProjectTrump.jar
