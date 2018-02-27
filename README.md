# Car Wash #
Ever wash your car only to have it rain the next day?  Each time you wash your car track it in Car Wash.  Car Wash
will log the wash, the current conditions, and the next 10 days conditions.  Using this data, Car Wash
will learn your luck and recommend ideal wash days.

Car Wash is a demo app with an exercise in learning:
  * Stream base architectures
  * Kafka
    * Setup and configuration
    * Producers
    * Consumers
    * Stream Processors
    * Connectors
  * Protobuf (or similar)
  * Spring 5 with WebFlux
  * Angular
  * PostgreSQL
  * Stream stuff
  * Okta
  * Maven
  * Flyway
  
Will eventually convert to:
  * Kotlin
  * Gradle
    
## Set Up ##
Initial project built using a Spring initializer via IntelliJ.

### webui Module ###
Uses Angular-cli via IntelliJ module.
Installed angular-cli using Brew before adding module:
```
$ brew install angular-cli
```  

#### Serve it up ####
Open terminal in **webui** module and run a local server via Angular-cli:
```
$ ng serve
```

Navigate to [http://localhost:4200](http://localhost:4200/).

### Kafka Installation ###
Adapted from [Kafka Documentation](https://kafka.apache.org/documentation/#introduction):

Install via Brew:
```
$ brew install kafka 
``` 

To have launchd start zookeeper now and restart at login:

```
$ brew services start zookeeper
```                            
Or, if you don't want/need a background service you can just run:
```
$ zkServer start
```

**Start Kafka (This Will START Zookeeper AUTOMATICALLY):** To have launchd start kafka now and restart at login:
```
$ brew services start kafka
```
Or, if you don't want/need a background service you can just run:
```
$ zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties \
  & kafka-server-start /usr/local/etc/kafka/server.properties
```

To test Kafka, create a test topic:
```
$ kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

List the Kafka topics:
```
$ kafka-topics --list --zookeeper localhost:2181
```

**Alternatively,** instead of manually creating topics you can also configure your brokers to auto-create topics when a non-existent topic is published to.

Send a test message by running a producer and typing messages into the 
console:
```
$ kafka-console-producer --broker-list localhost:9092 --topic test
> My Test Message
> Another Test Message
```

Start a consumer to receive the messages produced on the console:
```
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```

#### Starting Multiple Kafka Servers ####
Following the Kafka guide, section 1.3 Step 6, it's possible to start multiple servers by
editing the ``/usr/local/etc/kafka/server-*.properties`` changing these attributes (incrementing id, port, and log)files:
```
    broker.id=1
    listeners=PLAINTEXT://:9093
    log.dir=/tmp/kafka-logs-1
```

Then, start Zookeeper separately and each server separately:
```
$ zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
$ kafka-server-start /usr/local/etc/kafka/server.properties
$ kafka-server-start /usr/local/etc/kafka/server-1.properties
$ kafka-server-start /usr/local/etc/kafka/server-2.properties
```

This starts 3 Kafka servers.  Now, create a new topic with a replication factor of 3 and 
describe what Kafka is doing:
```
$ kafka-topics --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic my-replicated-topic
$ kafka-topics --describe --zookeeper localhost:2181 --topic my-replicated-topic
```

### Postgres Installation ###
Install PostgreSQL 10.1 via brew
```
$ brew install postgresql
```

Start the base install server:
```
$ pg_ctl -D /usr/local/var/postgres start
```
That will create a default postgres DB at ``/usr/local/var/postgres``

Init a 'defaultdb':

```
$ initdb /usr/local/var/defaultdb 
```

Stop existing running DB and start the defaultdb and log to /var/log/defaultdb.log:
```
$ pg_ctl -D /usr/local/var/postgres stop
$ pg_ctl -D /usr/local/var/defaultdb -l /usr/local/var/log/defaultdb.log start
$ createdb carwash
$ psql carwash
$ createuser -P admin
$ psql carwash
psql (10.1)
Type "help" for help.

carwash=# grant all privileges on database carwash to admin;
```
### Default JDBC String
``jdbc:postgresql://localhost:5432/carwash``


