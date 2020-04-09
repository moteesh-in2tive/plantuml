***diagrams.net build of PlantUML***

Strips out eggs, games, licensing, GPL licensed code, etc. Build of server version found under [server repo packages section](https://github.com/jgraph/plantuml/packages).

***diagrams.net specific changes:***

In order for deploying our custom PlantUML jars to Github, change the version in build.xml (in `mvn_windows` and `mvn_unix` targets). Then in PlantUML Server repository, change the dependency version to match the new version (in `pom.xml` find `plantuml` dependency) 
