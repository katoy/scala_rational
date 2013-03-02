/*
 * Main.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rational

import rational.Rational._ // コンパニオンオブジェクトのimport
import scala.BigDecimal.RoundingMode._

object Main extends App {

    sample()

    def sample() {
        println("Rational(2.0)=" + Rational(2.0))
        println("Rational(2.1)=" + Rational(2.1))
        println("Rational(2)=" + Rational(2))
        println("Rational(-2.0)=" + Rational(-2.0))
        println("Rational(-2.1)=" + Rational(-2.1))
        println

        val r = Rational(5, 10)
        println("(r = Rational(5, 10)) =" + r)
        println("r.bigDecimalValue(5, HALF_DOWN))=" + r.bigDecimalValue(5, HALF_DOWN))
        println("Rational(BigDecimal(1.23).setScale(2, HALF_UP))=" + Rational(BigDecimal(1.23).setScale(2, HALF_UP)))
        println("Rational(BigDecimal(1.23).setScale(1, HALF_UP))=" + Rational(BigDecimal(1.23).setScale(1, HALF_UP)))
        println("Rational(BigDecimal(1.23).setScale(3, HALF_UP))=" + Rational(BigDecimal(1.23).setScale(3, HALF_UP)))
        println

        println("r + Rational(1.2) =" + (r + Rational(1.2)))
        println("r + 1.2=" + (r + 1.2))
        // println(r + BigDecimal(1.2))
        println("r + BigDecimal(1.2).setScale(1, HALF_DOWN)=" + (r + BigDecimal(1.2).setScale(1, HALF_DOWN)))
        println("r + BigDecimal(1.2).setScale(2, HALF_DOWN)=" + (r + BigDecimal(1.2).setScale(2, HALF_DOWN)))
        println("r - BigDecimal(0.45).setScale(2,HALF_DOWN)=" + (r - BigDecimal(0.45).setScale(2, HALF_DOWN)))
        println("r * 1.2=" + (r * 1.2))
        println("r / 4=" + (r / 4))
        println

        println("2 + r=" + (2 + r))
        println("2.0 + r=" + (2.0 + r))
        println("r + 2=" + (r + 2))
        println("BigDecimal(1.5) + r=" + ((BigDecimal(1.5) + r)))
        println

        println("(r == Rational(50, 100))=" + (r == Rational(50, 100)))
        println("(r == Rational(6, 10))=" + (r == Rational(6, 10)))
        println("(r == Rational(5, 10))=" + (r == Rational(5, 10)))
        println

        println("r=" + r)
        println("Rational(1.1)=" + Rational(1.1))
        println("1.1 * r=" + (1.1 * r))
        println("r * -1.1=" + (r * -1.1))
        println("Rational(0.9) * Rational(123456790)=" + (Rational(0.9) * Rational(123456790)))
        println("Rational(0.9) * Rational(123456790) / 9.0=" + (Rational(0.9) * Rational(123456790) / 9.0))
        println("Rational(0.9) * Rational(123456790)+0.1=" + (Rational(0.9) * Rational(123456790)+0.1))
        println("Rational(1, 3).toPeriodString=" + Rational(1, 3).toPeriodString)
        println("Rational(1, 10).toPeriodString=" + Rational(1, 10).toPeriodString)
        println("Rational(355, 113).toPeriodString=" + Rational(355, 113).toPeriodString)
    }
}

     
