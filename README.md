

travix : [![Build Status](https://travis-ci.org/katoy/scala_rational.png)](https://travis-ci.org/katoy/scala_rational) 

scala-rational
=========


### Contents

scala-rational project includes:
- Sample Scala codes.
- [ScalaTest](http://www.scalatest.org/) examples
- Pre-configured settings for developing with IntelliJ IDE
- A command for packaging projects with [sbt-pack](http://github.com/xerial/sbt-pack) plugin.
  - `sbt-pack` also generates installation scripts for you programs.

### Usage

Download tar.gz archive of this project, then extract the contents:

**Run tests**

    $ sbt test

**Run tests when updates of source codes are detected**
   
    $ sbt "~test"

**Run specific tests maching a pattern**

    $ sbt "~test-only *HelloTest"

**Run tagged test only**

    $ sbt "~test -- include(test1)"

**Create a runnable package**
  
    $ sbt pack
    $ target/pack/bin/hello
    Hello World!!

**Install your program**

    $ sbt pack
    $ cd target/pack; make install
    $ ~/local/bin/hello
    Hello World!!

**Create IntelliJ project files**

    $ sbt gen-idea

**Add dendent libraries**

Edit `project/Build.scala`, then add libraries to `libraryDependences` variable.

### Customize

Rename the project name defined in `project/Build.scala` as you like.

### ToDo

- Add examples using command line parser

