# Versioning Module


## Installation
The module isn't on the Maven Central Repository, therefore a third-party repository is required.

You have to add the [JitPack](https://jitpack.io/) repository to you project's pom.xml file.
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

And this dependency.
```xml
<dependency>
	<groupId>com.github.aziascreations</groupId>
	<artifactId>module-versioning</artifactId>
	<version>-SNAPSHOT</version>
</dependency>
```

If you are using another build automation tool or if want to download it, go check the JitPack page: https://jitpack.io/#aziascreations/module-versioning/

## Usage
Firstly, you have to import `com.azias.module.version.Version` in your code.

Now, you can initialize the Version object in 4 different ways.
```java
//Will be "0.0.0" by default, can be edited manually later.
Version var1 = new Version();

//???
Version var1 = new Version(1, 0, 0);

//???
Version var1 = new Version(1, 0, 0, "rc0", "build0");

//???
Version var1 = new Version("1.0.0-rc0+build0");
```
They will throw an `IllegalVersionFormatException` if you don't format the version string(s) correctly.

To ensure that this doesn't happen, you can check the [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html) specs.

## License
[Public Domain](LICENSE)