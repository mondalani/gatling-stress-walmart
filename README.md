Gatling DSE Stress
==============

## Building
To build a jar run `gradle clean build`.  The jar will be found in `build/libs/gatling-dse-1.0.jar`


## Running a Simulation
First build the jar using gradle then run the jar with the path of the sim name `java -jar gatling-dse-1.0.jar -s {SimPath}` and example is `java -jar gatling-dse-1.0.jar -s sims.examples.cql.WriteOrderSimulation`

## Configuration
Project configs can be found in the `src/main/resources` the `application.conf` is the file to set the Simulation and Cassandra settings.  

During run you can override part or all of the application settings by using `-Dconfig.file={filePath}`.  If you want to override a single setting only just use the path of the config ie `-Dcassandra.hosts=127.0.0.1`.  This single setting can be used for any value in the `gatling.conf` file as well.


### Listing Available Sims in Jar
Run `java -cp gatling-dse-1.0.jar Utils ListSims`

### Showing Default Configurations
Run `java -cp gatling-dse-1.0.jar Utils ShowConf`


## Setup in IDEs
`gradle eclipse` or `gradle idea`


## Requirements
- Java 1.8+
- Gradle 2.10+
- [gatling-dse-plugin](https://github.com/riptano/gatling-dse-plugin) - currently included in the `lib/` folder until a better method is found

Running `gradle assemble` will download all of the needed libraries including Scala to your local machine.

## Questions or Requests
Please use the [Issues section](https://github.com/riptano/gatling-dse-stress/issues) to add any questions on usage or requests

There is also a `#gatling-dse` Slack channel where questions can be asked.

### Coming Soon..
- Using with Jenkins
- Screencasts