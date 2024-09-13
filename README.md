# EntityMoveEvent
A handy library that allows spigot plugin developers to listen to entities moving 

## Installation:

For maven:

```xml
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>

		<dependency>
		    <groupId>com.github.Dqmino</groupId>
		    <artifactId>EntityMoveEvent</artifactId>
		    <version>master-SNAPSHOT</version>
		</dependency>
```

As for gradle:

```gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.Dqmino:EntityMoveEvent:master-SNAPSHOT'
	}
```


## Usage:
Call `EntityMoveEventBase.inject(this);` in your plugin's onEnable() method.
Then simply call `EntityMoveEventBase.getInstance().markEntity(entity);` to mark the entity specified for listening, and then listen to `EntityMoveEvent` as if it was a regular spigot event.

## Notice:
If you mark many entities, the server may lag.
