// See http://gist.github.com/liesen/scala-kata-util/blob/ac1007c2f017a7914e60b097743f5318e85ea792/src/util/Rational.scala
//     http://d.hatena.ne.jp/zetta1985/20091127/1259333184
//     http://cocomonrails.blogspot.com/2009/11/scala-rational.html
//     http://ja.doukaku.org/9/lang/scala/

package rational

import language.implicitConversions

/** 有理数 */
object Rational {

  /**指定された分子・分母で新しい Rational を生成して返す。
   * @param n 分子
   * @param d 分母
   */
  def apply(n: Long, d: Long) = new Rational(n, d)

  /**分子が指定された Long、分母が1の新しい Rational を生成して返す。
   * @param n 分子
   */
  def apply(n: Long) = new Rational(n, 1L)

  // Double を文字列で処理して Rational に変換する。
  private val decimalFormat = new java.text.DecimalFormat( "0.############E0" ); // 13 個の #
  private val dtE = """([+-]?)(\d*)(\.?)(\d*?)E([+-]?\d+)""".r

  /**指定された double で新しい Rational を生成して返す。
   * @param d 任意精度の符号付き不動点小数
   */
  def apply(d: Double) = {
    decimalFormat.format(d) match {
      case dtE(s, n0, dot, n1, m) => {
          val sign = (if (s == "-") -1 else 1)
          val p = m.toLong
          val v = (n0+n1).toLong * sign
          if (p - n1.length > 0) new Rational((1.0 * v * Math.pow(10, p - n1.length)).toLong, 1)
          else new Rational(v, Math.pow(10, -p + n1.length).toLong)
        }
      case _ => throw new Exception(d.toString)
    }
  }

  /**指定された BigDecimal で新しい Rational を生成して返す。
   * @param d 任意精度の符号付き10進数
   */
  def apply(d: BigDecimal): Rational =
    new Rational(d.bigDecimal.movePointRight(d.scale).intValue,
                 Math.pow(10, d.scale).toInt)

  /**指定された String で新しい Rational を生成して返す。
   * @param d 任意精度の符号付き10進数
   */
  def apply(str: String): Rational = Rational.parseStr(str)

  // 以下、暗黙の型変換メソッド
  /**指定された Int を、Rational に変換して返す。*/
  implicit def int2Rational(n: Int) = Rational(n)
  /**指定された Long を、Rational に変換して返す。*/
  implicit def long2Rational(n: Long) = Rational(n)
  /**指定された Double を、Rational に変換して返す。*/
  implicit def double2Rational(n: Double) = Rational(n)
  /**指定された BigDecimal を、Rational に変換して返す。*/
  implicit def bigDeciaml2Rational(dec: BigDecimal) = Rational(dec)
  /**指定された String を、Rational に変換して返す。*/
  implicit def str2Rational(str: String) = Rational(str)

  // 循環小数 -> Rational 変換関数
  // 1.23333...  は "1.2{3}" のように表記する
  val periodRegStr = """([+-]?)(\d+)\.(\d*)\{(\d+)\}""".r
  val doubleRegStr = """([+-]?)(\d+\..*)""".r
  val longRegStr = """([+-]?)(\d+)""".r
  /** 数値文字列 (Long, Double, 循環小数) から Rational としてパースする。
   * 1.23333...  は "1.2{3}" のように表記する。
   */
  def parseStr(str: String) =
    str match {
      case periodRegStr(s, a, b, c) => {
          val sign = if (s=="-") -1 else 1
          val intPart = a.toLong
          val nonRepeatPart =
            if (b == "") new Rational(0,1)
          else new Rational(b.toLong,
                            Math.pow(10.0, b.length).toInt)
          val repeatPart = new Rational(c.toLong,
                                        (Math.pow(10.0, c.length)-1).toLong) / Math.pow(10.0, b.length).toLong
          sign * (intPart + nonRepeatPart + repeatPart)
        }
      case longRegStr(s, a) => Rational(str.toLong)
      case doubleRegStr(s, a) => Rational(str.toDouble)
      case _ => sys.error("Invalid argument: [" + str + "]")
    }
}

/** 有理数 */
class Rational private(n: Long, d: Long) extends Ordered[Rational] {

  require(d != 0) // 分母が0の場合はエラー

  /** a と b の最大公約数を返す。*/
  private def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)

  /** 最大公約数 */
  private val gratestCommonDiviser = gcd(n.abs, d.abs);

  /** 分子 */
  val numerator: Long = (if (d < 0) -n else n) / gratestCommonDiviser
  /** 分母 */
  val denominator: Long  = (if (d < 0) -d else d) / gratestCommonDiviser

  //  単項演算子 +
  def unary_+ = new Rational(numerator, denominator)
  //  単項演算子 -
  def unary_- = new Rational(-numerator, denominator)

  /** Rational の加算 */
  def + (that: Rational) = new Rational(numerator * that.denominator + that.numerator * denominator,
                                        denominator * that.denominator
  )
  def + (num: Long): Rational = this + Rational(num)
  def + (num: Double): Rational = this + Rational(num)
  def + (num: BigDecimal): Rational = this + Rational(num)

  /** Rational の減算 */
  def - (that: Rational): Rational = this + (- that)
  def - (num: Long): Rational = this - Rational(num)
  def - (num: Double): Rational = this - Rational(num)
  def - (num: BigDecimal): Rational = this - Rational(num)

  /** Rational の乗算 */
  def * (that: Rational): Rational = new Rational(numerator * that.numerator,
                                                  denominator * that.denominator)
  def * (num: Long): Rational = this * Rational(num)
  def * (num: Double): Rational = this * Rational(num)
  def * (num: BigDecimal): Rational = this * Rational(num)

  /** 乗算の逆数 */
  def inv = {
    require(numerator != 0)
    new Rational(denominator, numerator)
  }

  /** Rational の除算 */
  def / (that: Rational): Rational = this * that.inv
  def / (num: Long): Rational = this / Rational(num)
  def / (num: Double): Rational = this / Rational(num)
  def / (num: BigDecimal): Rational = this / Rational(num)

  //    def > (that: Rational): boolean = (this - that).numerator > 0
  //    def >= (that: Rational): boolean = (this - that).numerator >= 0
  //    def < (that: Rational): boolean = ! (this >= that)
  //    def <= (that: Rational): boolean = ! (this > that)
  def compare(that: Rational) = {
    val d = (this.numerator * that.denominator) - (that.numerator * this.denominator)
    if (d > 0) {
      1
    } else if (d == 0) {
      0
    } else {
      -1
    }
  }

  /** Rational の冪算 */
  def ** (that: Rational): Rational = {
    val thatVal = 1.0 * that.numerator / that.denominator
    if ((thatVal == thatVal.toInt) && (thatVal > 0)) {
      new Rational(Math.pow(1.0 * numerator, 1.0 * that.numerator/that.denominator).toLong,
                   Math.pow(1.0 * denominator, 1.0 * that.numerator/that.denominator).toLong)
    } else {
      val v = Math.pow(1.0 * numerator/denominator, 1.0 * that.numerator/that.denominator)
      Rational(v)
    }
  }
  def ** (num: Long): Rational = this ** Rational(num)
  def ** (num: Double): Rational = this ** Rational(num)
  def ** (num: BigDecimal): Rational = this ** Rational(num)

  /** 指定された Any と この Rational が等値である場合、true を返す。 */
  override def equals(other: Any): Boolean =
    other match {
      case that: Rational =>
        (numerator == that.numerator) && (denominator == that.denominator)
      case _ => false
    }

  /** この Rational のハッシュコードを返す。 */
  override def hashCode: Int = (41 * (41 + numerator) + denominator).toInt

  /** この Rational を Double に変換する。*/
  def doubleValue : Double = numerator.toDouble / denominator

  import scala.BigDecimal.RoundingMode._
  /** この Rational を BigDecinal に変換する。*/
  def bigDecimalValue(scale: Int, rMode: RoundingMode) : BigDecimal =
    (BigDecimal(numerator).setScale(scale, rMode) /
     BigDecimal(denominator).setScale(scale, rMode)).setScale(scale, rMode)

  /** この Rational の文字列表現を返す。 */
  override def toString =
    if (denominator == 1) numerator.toString else (numerator + "/" + denominator)

  /** 循環小数表記に変換する。 */
  def toPeriodString:String = {
    import scala.collection.mutable._

    var result = new ListBuffer[Long]
    val mods   = new ArrayBuffer[Long]
    val a = this.numerator.abs
    val b = this.denominator.abs
    mods += a % b
    def l(v:Long):Long = {
      result += (v*10) / b
      val mod = (v*10) % b
      if (mods.contains(mod)) { mods.indexOf(mod) }
      else if (mod == 0) { -1 }
      else { mods += mod; l(mod) }
    }
    val i = l(a % b).toInt

    var p = result.mkString("")
    if(i != -1) p = p.substring(0, i) + "{" + p.substring(i) + "}"
    (if (this.numerator < 0) "-" else "") + (a / b) +  (if (b != 1) {"." + p} else "")
  }

  // TODO:
  // n! (階乗) ガンマ関数 etc...
}
