/*
 * RationalJUnitTest.scala
 */

package rational
import org.junit._, Assert._

import rational.Rational._ // コンパニオンオブジェクトのimport
import scala.BigDecimal.RoundingMode._

class RationalTest {

    @Before
    def setUp: Unit = {
    }

    @After
    def tearDown: Unit = {
    }

    @Test
    def constract001 = {
        val r = Rational(1,1)
        assertEquals((r.numerator, r.denominator), (1, 1))
    }

    @Test
    def constract002 = {
        val r = Rational(-1,1)
        assertEquals((r.numerator, r.denominator), (-1, 1))
    }
    @Test
    def constract003 = {
        val r = Rational(10,10)
        assertEquals((r.numerator, r.denominator), (1, 1))
    }
    @Test
    def constract004 = {
        val r = Rational(10,2)
        assertEquals((r.numerator, r.denominator), (5, 1))
    }
    @Test
    def constract005 = {
        val r = Rational(2,10)
        assertEquals((r.numerator, r.denominator), (1, 5))
    }
    @Test(expected = classOf[IllegalArgumentException])
    def constract006 = {
        val r = Rational(2,0)
        assertEquals((r.numerator, r.denominator), (1, 1)) // never reach
    }

    @Test
    def constractInt001 = {
        val r = Rational(1)
        assertEquals((r.numerator, r.denominator), (1, 1))
    }
    @Test
    def constractInt002 = {
        val r = Rational(-1)
        assertEquals((r.numerator, r.denominator), (-1, 1))
    }
    @Test
    def constractInt003 = {
        val r = Rational(12345678)
        assertEquals((r.numerator,r.denominator), (12345678, 1))
    }
    @Test
    def constractInt004 = {
        val r = Rational(0)
        assertEquals((r.numerator, r.denominator), (0, 1))
    }

    @Test
    def constractLong001 = {
        val r = Rational(Integer.MAX_VALUE + 1L)
        assertEquals((r.numerator,r.denominator),(Integer.MAX_VALUE + 1L, 1))
    }
    @Test
    def constractLong002 = {
        val r = Rational(Integer.MIN_VALUE - 1L)
        assertEquals((r.numerator, r.denominator), (Integer.MIN_VALUE -1L, 1))
    }
    @Test
    def constractLong003 = {
        val r = Rational(123456789012L)
        assertEquals((r.numerator, r.denominator),(123456789012L, 1))
    }

    @Test
    def constractLong004 = {
        val r = Rational(0L)
        assertEquals((r.numerator, r.denominator),(0, 1))
    }

    @Test
    def constractDouble001 = {
        val r = Rational(1.234567)
        assertEquals((r.numerator, r.denominator),(1234567, 1000000))
    }
    def constractDouble002 = {
        val r = Rational(-1.234567)
        assertEquals((r.numerator, r.denominator),(-1234567, 1000000))
    }
    @Test
    def constractDouble003 = {
        val r = Rational(0.003)
        assertEquals((r.numerator, r.denominator),(3, 1000))
    }
    @Test
    def constractDouble004 = {
        val r = Rational(123.47)
        assertEquals((r.numerator,r.denominator), (12347, 100))
    }
    @Test
    def constractDouble005 = {
        val r = Rational(12345678.12345678)
        assertEquals(r.doubleValue, 12345678.12345678, 1E-5)
    }

    @Test
    def constractDouble006 = {
        val r = Rational(123L)
        assertEquals((r.numerator,r.denominator), (123, 1))
    }

    @Test
    def add001 = {
        val r = Rational(1, 2) + 1
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def add002 = {
        val r = 1 + Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def add003 = {
        val r = Rational(1, 2) + 1L
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def add004 = {
        val r = 1L + Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def add005 = {
        val r = Rational(1, 2) + 1.2
        assertEquals((r.numerator,r.denominator), (17, 10))
    }
    @Test
    def add006 = {
        val r = 1.2 + Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (17, 10))
    }
    @Test
    def add007 = {
        val r = Rational(1,3) + Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (5, 6))
    }

    @Test
    def sub001 = {
        val r = Rational(1, 2) - 1
        assertEquals((r.numerator,r.denominator), (-1, 2))
    }
    @Test
    def sub002 = {
        val r = 1 - Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (1, 2))
    }
    @Test
    def sub003 = {
        val r = Rational(1, 2) - 1L
        assertEquals((r.numerator,r.denominator), (-1, 2))
    }
    @Test
    def sub004 = {
        val r = 1L - Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (1, 2))
    }
    @Test
    def sub005 = {
        val r = Rational(1, 2) - 1.2
        assertEquals((r.numerator,r.denominator), (-7, 10))
    }
    @Test
    def sub006 = {
        val r = 1.2 - Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (7, 10))
    }
    @Test
    def sub007 = {
        val r = Rational(1,3) - Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (-1, 6))
    }

    @Test
    def mul001 = {
        val r = Rational(1, 2) * 3
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def mul002 = {
        val r = 3 * Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def mul003 = {
        val r = Rational(1, 2) * 3L
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def mul004 = {
        val r = 3L * Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def mul005 = {
        val r = Rational(1, 2) * 0.5
        assertEquals((r.numerator,r.denominator), (1, 4))
    }
    @Test
    def mul006 = {
        val r = 0.5 * Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (1, 4))
    }
    @Test
    def mul007 = {
        val r = Rational(1,3) * Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (1, 6))
    }
    @Test
    def mul008 = {
        val r = Rational(2,3) * Rational(3, 4)
        assertEquals((r.numerator,r.denominator), (1, 2))
    }

    @Test
    def div001 = {
        val r = Rational(1, 2) / 3
        assertEquals((r.numerator,r.denominator), (1, 6))
    }
    @Test
    def div002 = {
        val r = 3 / Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (6, 1))
    }
    @Test
    def div003 = {
        val r = Rational(1, 2) / 3L
        assertEquals((r.numerator,r.denominator), (1, 6))
    }
    @Test
    def div004 = {
        val r = 3L / Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (6, 1))
    }
    @Test
    def div005 = {
        val r = Rational(1, 2) / 0.5
        assertEquals((r.numerator,r.denominator), (1, 1))
    }
    @Test
    def div006 = {
        val r = 0.5 / Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (1, 1))
    }
    @Test
    def div007 = {
        val r = Rational(1,3) / Rational(1, 2)
        assertEquals((r.numerator,r.denominator), (2, 3))
    }
    @Test
    def div008 = {
        val r = Rational(2,3) / Rational(3, 4)
        assertEquals((r.numerator,r.denominator), (8, 9))
    }

    @Test
    def unary01 = {
        val r = - Rational(3, 4)
        assertEquals((r.numerator,r.denominator), (-3, 4))
    }

    @Test
    def invert01 = {
        val r = Rational(3, 4).inv
        assertEquals((r.numerator,r.denominator), (4, 3))
    }

    @Test
    def pow01 = {
        val r = Rational(3, 4) ** 2
        assertEquals((r.numerator,r.denominator), (9, 16))
    }
    @Test
    def pow02 = {
        val r = Rational(27, 8) ** Rational(1,3)
        assertEquals((r.numerator,r.denominator), (3, 2))
    }
    @Test
    def pow03 = {
        val r = Rational(2) ** Rational(1,2)
        val delta = (r.doubleValue - Math.pow(2.0, 0.5)).abs
        assertTrue(delta <= (1.0E-13))
        assertTrue(delta > 1.0E-14)
    }
    @Test
    def pow04 = {
        val r = Rational(9999) ** Rational(1,2)
        val delta = (r.doubleValue - Math.pow(9999.0, 0.5)).abs
        assertTrue(delta <= (1.0E-11))
        assertTrue(delta > 1.0E-12)
    }

    @Test
    def period01 = {
        val r = Rational.parseStr("0.{3}")
        assertEquals((r.numerator,r.denominator), (1, 3))
    }
    @Test
    def period02 = {
        val r = Rational.parseStr("0.{142857}")
        assertEquals((r.numerator,r.denominator), (1, 7))
    }
    @Test
    def period03 = {
        val r1 = Rational.parseStr("0.{3}")
        val r2 = Rational.parseStr("0.{7}")
        val r3 = r1 * r2
        assertEquals((r3.numerator,r3.denominator), (7, 27))

        assertEquals(r3.toPeriodString, "0.{259}")
        assertEquals((r3.numerator,r3.denominator), (7, 27))
    }
    @Test
    def period04 = {
        val r1 = Rational.parseStr("1.{3}")
        val r2 = Rational.parseStr("1.{7}")
        val r3 = r1 * r2
        assertEquals((r3.numerator,r3.denominator), (64, 27))

        assertEquals(r3.toPeriodString, "2.{370}")
        assertEquals((r3.numerator,r3.denominator), (64, 27))
    }
    @Test
    def period05 = {
        val r = Rational(4, 3)
        assertEquals(r.toPeriodString, "1.{3}")
    }
    @Test
    def period06 = {
        val r = Rational(-4, 3)
        assertEquals(r.toPeriodString, "-1.{3}")
    }
    @Test
    def period07 = {
        val r = -Rational.parseStr("1.{3}")
        assertEquals((r.numerator,r.denominator), (-4, 3))
    }
    @Test
    def period08 = {
        val r1 = Rational(-1, 3)
        assertEquals(r1.toPeriodString, "-0.{3}")

        val r2 = - Rational.parseStr("0.{3}")
        assertEquals((r2.numerator,r2.denominator), (-1, 3))
    }
    @Test
    def period09 = {
        val r1 = Rational(1, 4)
        assertEquals(r1.toPeriodString, "0.25")
    }
    @Test
    def period10 = {
        val r1 = Rational(2, 1)
        assertEquals(r1.toPeriodString, "2")
    }
    @Test
    def period11 = {
        val r1 = Rational(6, 5)
        assertEquals(r1.toPeriodString, "1.2")
    }

    @Test
    def str01 = {
        val r = Rational("1")
        assertEquals((r.numerator,r.denominator), (1, 1))
    }
    @Test
    def str02 = {
        val r = Rational("1.2")
        assertEquals((r.numerator,r.denominator), (6, 5))
    }
    @Test
    def str03 = {
        val r = Rational("0.25")
        assertEquals((r.numerator,r.denominator), (1, 4))
    }
    @Test
    def str04 = {
        val r = Rational("0.{3}")
        assertEquals((r.numerator,r.denominator), (1, 3))
    }
    @Test
    def str05 = {
        val r = Rational("1.{3}")
        assertEquals((r.numerator,r.denominator), (4, 3))
    }

    @Test
    def str06 = {
        val r = Rational("-1.{3}")
        assertEquals((r.numerator,r.denominator), (-4, 3))
    }
    @Test
    def str07 = {
        val r = Rational("1.2")
        assertEquals((r.numerator,r.denominator), (6, 5))
    }

    @Test
    def str08 = {
        val r = Rational("-1.2")
        assertEquals((r.numerator,r.denominator), (-6, 5))
    }

    @Test
    def str09 = {
        val r = Rational(22, 7)
        assertEquals(r.toPeriodString, "3.{142857}")
    }
    @Test
    def str10 = {
        val r = Rational(355, 113)
        assertEquals(r.toPeriodString,
                     "3.{1415929203539823008849557522123893805309734513274336283185840707964601769911504424778761061946902654867256637168}")
    }
    @Test
    def str11 = {
        val r = Rational(104348, 33215)
        assertEquals(r.toPeriodString, "3.1{415926539214210447087159}")
    }
    @Test
    def str12 = {
        val r = Rational(1, 99989)  //983
        assertEquals(r.toPeriodString.length, 99992) // 巡回部は 982 桁
    }
    // 9967, 99989

    @Test
    def compare01 = {
        assertTrue(Rational(1,3) == Rational("0.{3}"))
        assertTrue(Rational(1,2) == Rational("0.4{9}"))
        assertTrue(Rational(1,2) == Rational("0.5"))
        assertTrue(Rational(1,2) == Rational("0.5{0}"))

        assertTrue(Rational(1,2) != Rational("0.51"))
    }

    @Test
    def compare02 = {
        assertTrue(Rational(1,3) > Rational(1,4))
        assertTrue(Rational(1,3) >= Rational(1,3))
        assertTrue(Rational(1,5) < Rational(1,4))
        assertTrue(Rational(1,4) <= Rational(1,4))
    }

    @Test
    def compare03 {
        assertTrue(Rational(1,2) > 0.49)
        assertTrue(Rational(1,2) >= 0.5)
        assertTrue(Rational(1,2) < 1)
        assertTrue(Rational(1,2) <= 0.5)

        assertFalse(Rational(1,2) <= 0.49)
        assertFalse(Rational(1,2) < 0.5)
        assertFalse(Rational(1,2) >= 1)
        assertFalse(Rational(1,2) > 0.5)

        assertTrue(Rational(1,2) > 0L)
        assertTrue(Rational(1,2) >= 0L)
        assertTrue(Rational(1,2) < 1L)
        assertTrue(Rational(1,2) <= 1L)
    }
    @Test
    def compare04 {
        assertTrue(0.51 > Rational(1,2))
        assertTrue(0.50 >= Rational(1,2))
        assertTrue(0.49 < Rational(1,2))
        assertTrue(0.50 <= Rational(1,2))

        assertFalse(0.51 <= Rational(1,2))
        assertFalse(0.50 < Rational(1,2))
        assertFalse(0.49 >= Rational(1,2))
        assertFalse(0.50 > Rational(1,2))

        assertTrue(1L > Rational(1,2))
        assertTrue(1L >= Rational(1,2))
        assertTrue(0L < Rational(1,2))
        assertTrue(0L <= Rational(1,2))
    }
    @Test
    def compare05 {
        assertTrue(Rational(1,2) < "0.5{1}")
        assertTrue(Rational(1,2) >= "0.4{9}")
        assertTrue(Rational(1,3) >= "0.{3}")

        assertFalse(Rational(1,2) >= "0.5{1}")
        assertFalse(Rational(1,2) < "0.4{9}")
        assertFalse(Rational(1,3) < "0.{3}")
    }

    @Test
    def calc001 = {
        var r1 = Rational(1)
        val ans3 = (r1 / 3)
        val ans = ans3 * 3
        assertEquals(ans3.toString, "1/3")
        assertEquals(ans3.doubleValue, 1.0/3.0, 0.01)
        assertEquals(ans.toString, "1")

        val ansx = r1 - BigDecimal(0.45).setScale(2, HALF_DOWN)
        assertEquals(ansx.doubleValue, 0.55, 0.001)
        assertEquals(ansx.toString, "11/20")

        val rx = Rational("0.{32}") - Rational("0.{1}") // 32/99 - 11/99
        assertEquals(rx.toPeriodString, "0.{21}")
        assertEquals(rx.toString, "7/33")  // 21/99

        assertEquals(Rational("0.{3}") * Rational("0.{2}"), Rational("0.{074}")) // 3/9 * 2/9 = 2/27
        assertTrue(Rational("0.{074}") == Rational(2,27))
    }

    // http://math-sci.hp.infoseek.co.jp/Math/inverse-table.txt
    //@Test
    def calc002 = {
        val RANGE_0 = 399900  // 2
        val RANGE_1 = 399999 // 100
        var ans = 0
        var m = 0
        for(i <- RANGE_0 to RANGE_1) {
            if (i % 1000 == 0) println(i)
            if ((i % 2 != 0) && (i % 3 !=0) && (i % 5 != 0) && (i % 7 != 0) &&
                (i % 11 != 0) && (i % 13 != 0) && (17 % 7 != 0) && (i % 19 != 0)) {
                val r = Rational(1, i)
                if (r.toPeriodString.length > m) { ans = i; m = r.toPeriodString.length}
                // Console.println(r.toString + " = " + r.toPeriodString)
            }
        }
        Console.println(ans)
        Console.println(Rational(1, ans).toPeriodString.length)
        Console.println(Rational(1, ans).toPeriodString)
    }
}
