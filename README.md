PlantUML
========
[![Build Status](https://travis-ci.org/plantuml/plantuml.png?branch=master)](https://travis-ci.org/plantuml/plantuml)

Generate UML diagram from textual description

PlantUML is a component that allows to quickly write :

 * [Sequence diagram](http://plantuml.com/sequence-diagram),
 * [Use case diagram](http://plantuml.com/use-case-diagram),
 * [Class diagram](http://plantuml.com/class-diagram),
 * [Activity diagram](http://plantuml.com/activity-diagram-beta),
 * [Component diagram](http://plantuml.com/component-diagram),
 * [State diagram](http://plantuml.com/state-diagram),
 * [Object diagram](http://plantuml.com/object-diagram),
 * [Deployment diagram](http://plantuml.com/deployment-diagram),
 * [Timing diagram](http://plantuml.com/timing-diagram)
 
And also:
 * [Activity diagram (legacy syntax)](http://plantuml.com/activity-diagram-legacy)
 * [Archimate diagram](http://plantuml.com/archimate)
 * [Gantt charts](http://plantuml.com/gantt-diagram)
 * [SALT UI mockups](http://plantuml.com/salt)

Furthermore:
 * [Hyperlinks and tooltips](http://plantuml.com/link)
 * [Creole](http://plantuml.com/creole): rich text, emoticons, unicode, icons
 * [OpenIconic icons](http://plantuml.com/openiconic)
 * [Sprite icons](http://plantuml.com/sprite)
 * [AsciiMath mathematical expressions](http://plantuml.com/ascii-math)

To know more about PlantUML, please visit http://plantuml.com/


draw.io specific changes:

In order for deploying our custom PlantUML jars to Github, change the version in build.xml (in `mvn_windows` and `mvn_unix` targets). Then in PlantUML Server repository, change the dependency version to match the new version (in `pom.xml` find `plantuml` dependency) 