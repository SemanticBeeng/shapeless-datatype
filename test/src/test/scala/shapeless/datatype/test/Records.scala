package shapeless.datatype.test

import com.google.protobuf.ByteString
import org.joda.time.Instant
import org.scalacheck._

object Records {
  case class Required(booleanField: Boolean,
                      intField: Int, longField: Long, floatField: Float, doubleField: Double,
                      stringField: String,
                      byteStringField: ByteString, byteArrayField: Array[Byte],
                      timestampField: Instant)
  case class Optional(booleanField: Option[Boolean],
                      intField: Option[Int], longField: Option[Long],
                      floatField: Option[Float], doubleField: Option[Double],
                      stringField: Option[String],
                      byteStringField: Option[ByteString], byteArrayField: Option[Array[Byte]],
                      timestampField: Option[Instant])
  case class Repeated(booleanField: List[Boolean],
                      intField: List[Int], longField: List[Long],
                      floatField: List[Float], doubleField: List[Double],
                      stringField: List[String],
                      byteStringField: List[ByteString], byteArrayField: List[Array[Byte]],
                      timestampField: List[Instant])
  case class Mixed(longField: Long, doubleField: Double, stringField: String,
                   longFieldO: Option[Long], doubleFieldO: Option[Double], stringFieldO: Option[String],
                   longFieldR: List[Long], doubleFieldR: List[Double], stringFieldR: List[String])
  case class Nested(longField: Long, longFieldO: Option[Long], longFieldR: List[Long],
                    mixedField: Mixed, mixedFieldO: Option[Mixed], mixedFieldR: List[Mixed])
  case class SeqTypes(array: Array[Int], list: List[Int], vector: Vector[Int])

  implicit val arbByteString = Arbitrary(Gen.alphaStr.map(ByteString.copyFromUtf8))
  implicit val arbInstant = Arbitrary(Gen.const(Instant.now()))

  def equivalent[A](xs: List[A], ys: List[A]): Boolean =
    xs.size == ys.size && xs.forall(ys.contains)
}
