

travix : [![Build Status](https://travis-ci.org/katoy/scala_rational.png?branch=master)](https://travis-ci.org/katoy/scala_rational)

scala-rational
==============

This is a rational library for scala.

- Copy from [http://sourceforge.jp/projects/ruby-xbrl/svn/view/trunk/ScalaApplication1/src/rational/](http://sourceforge.jp/projects/ruby-xbrl/svn/view/trunk/ScalaApplication1/src/rational/)
- See also [http://youichi-kato.cocolog-nifty.com/blog/2009/12/scala-6e3c.html](http://youichi-kato.cocolog-nifty.com/blog/2009/12/scala-6e3c.html)


これは 分数、循環小数を扱える scala ライブラリーです。

このライブラリーでは 循環小数の循環部を {, } で囲んで表現します。

例： 0.33333...   -> 0.{3}

scala コード中では次のようなに記述します。
- Rational(n)    -> 整数のｎと同等です。 (n は整数)
- Rational(n, m) -> 分数 n / m と藤堂です。 (ｎは整数、 m は 0 以外の整数)
- Rational("0.3") -> 小数 0.3 と同等です。
- Rational("0.{3}") -> 循環小数 0.33333... と同等です。
- Rational(1,3) と Rational("0.{3}") は同等です。

Rational は 四則演算、比較が可能です。
- Rational(1, 2) + Rational(1, 3)  -> Rationa(5, 6), 0.8333333.... と同等です。
- Rational(1, 2) - Rational(1, 3)　-> Rational(1, 6), 0.166666... と同等です。
- Rational(1, 2) * Rational(1, 3)　-> Rational(1, 6), と同等です。
- Rational(1, 2) / Rational(1, 3)　-> Rationa(3, 2), 1.5 と同等です。
- Rational(1, 3) == Rational(2, 6)
- Rational(1, 2) > Rational(1, 3)
- Rational(1, 3) == Rational("0.{3}")
- Rational(1) == Rational("0.{9}")
- Rational(1, 3) != Rational("0.3333333333333333333")

src/main/scala/Main.scala や src/test/scala/RationalTest.scala  を参照してください。

### Contents

scala-rational project includes:
- Sample Scala 
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

    $ sbt "~test-only *Test"

**Run tagged test only**

    $ sbt "~test -- include(test1)"

**Create a runnable package**
  
    $ sbt pack
    $ target/pack/bin/Rational


**Install your program**

    $ sbt pack
    $ cd target/pack; make install
    $ ~/local/bin/rational


**Create IntelliJ project files**

    $ sbt gen-idea

**Create API doc**

    % sbt doc
	pl

**Add dendent libraries**

Edit `project/Build.scala`, then add libraries to `libraryDependences` variable.

### Customize

Rename the project name defined in `project/Build.scala` as you like.

### ToDo

- 計算記(GUI) をつくる。
- 数式表Ｋ(コマンドライン)をつくる。
- 他言語実装 (node.js, ruby ...) をつくる。



