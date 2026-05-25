# UNIPDS — Post-Graduation Java Exercises

A Maven monorepo of standalone Java exercises from the UNIPDS post-graduation program. Each module under `modules/` covers a specific topic and can be built and run independently.

## Prerequisites

- JDK 25 (or compatible JDK with `java.lang.IO` support)
- Maven 3.9+

## Build all modules

```bash
mvn clean compile
```

## Run a module

From the module directory:

```bash
cd modules/<module-name>
mvn exec:java -Dexec.mainClass=<main-class>
```

From the repo root:

```bash
mvn -pl modules/<module-name> exec:java -Dexec.mainClass=<main-class> -Dexec.workingDirectory=modules/<module-name>
```

Example:

```bash
cd modules/oop-patterns
mvn exec:java -Dexec.mainClass=com.mathffreitas.patterns.Main
```

## Module index

| Module | Topic | Main class |
|--------|-------|------------|
| [java-basics](modules/java-basics) | Primitives and variables | `com.mathffreitas.basics.Main` |
| [strings-and-text](modules/strings-and-text) | String API | `com.mathffreitas.strings.Main` |
| [oop-patterns](modules/oop-patterns) | OOP, Factory, Builder, CSV/JSON I/O | `com.mathffreitas.patterns.Main` |
| [inheritance-encapsulation](modules/inheritance-encapsulation) | Inheritance and encapsulation | `com.mathffreitas.inheritance.Main` |
| [collections-framework](modules/collections-framework) | List, Set, Map | `com.mathffreitas.collections.Main` |
| [repository-streams](modules/repository-streams) | Repository pattern, Optional, Streams | `com.mathffreitas.repository.Main` |
| [date-time](modules/date-time) | java.time API | `com.mathffreitas.datetime.Main` |
| [reflection](modules/reflection) | Reflection and annotations | `com.mathffreitas.reflection.Main` |
| [regex](modules/regex) | Regular expressions | `com.mathffreitas.regex.Main` |
| [cryptography](modules/cryptography) | AES encryption | `com.mathffreitas.crypto.Main` |
| [file-writing](modules/file-writing) | FileWriter, BufferedWriter | `com.mathffreitas.filewriting.Main` |
| [file-reading](modules/file-reading) | IO vs NIO benchmarks | `com.mathffreitas.filereading.Main` |
| [advanced-collections](modules/advanced-collections) | Streams, groupingBy, in-memory DB | `com.mathffreitas.advancedcollections.Main` |
| [http-client](modules/http-client) | HttpClient, REST | `com.mathffreitas.http.Main` |
| [serialization](modules/serialization) | Object serialization | `com.mathffreitas.serialization.Main` |
| [integration-capstone](modules/integration-capstone) | HTTP server/client, sockets, Gson | `com.mathffreitas.integration.Main` |

## Project structure

```
unipds/
├── pom.xml              # Parent POM (plugin & dependency management)
├── modules/
│   ├── java-basics/
│   ├── oop-patterns/
│   └── ...
└── README.md
```

Each module follows the standard Maven layout:

```
modules/<name>/
├── pom.xml
├── README.md
└── src/main/java/com/mathffreitas/<package>/
```

Runtime artifacts (`benchmark.txt`, `pix.ser`, `output.json`) are written to each module's `target/` directory.
