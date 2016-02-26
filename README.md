# Hair Salon

#### Web app for a hair salon to assign clients to stylists using Java and Postgres | February 26, 2016

#### Authors

## Description
An app for a hair salon. The owner can add to a list of stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist.

## Setup/Installation Requirements

Clone this repository.
```
$ cd ~/Desktop
$ git clone https://github.com/annarbecker/hair-salon-java.git
$ cd hair-salon-java
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `hair_salon` database:
```
$ psql
# CREATE DATABASE hair_salon;
$ psql hair_salon < hair_salon.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

Open your web browser of choice to localhost:4567

Make sure you have Java and Gradle installed.
  * For Java:
      * Download and install [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
      * Download and install [Java JRE](http://www.java.com/en/)
  * For Gradle: if you are using Homebrew on Mac:
      * $ brew update
      * $ brew install gradle




## Technologies Used

Java, Spark, Junit, Velocity, Fluentlenium, Bootstrap, Postgres

### License

This software is licensed under the MIT license.

Copyright (c) 2016 Anna Becker

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
