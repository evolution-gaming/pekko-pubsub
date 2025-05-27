package com.evolution.cluster.pubsub

import com.evolution.serialization.SerializedMsg
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import scodec.bits.ByteVector

class PubSubSerializerSpec extends AnyFunSuite with Matchers {

  private val serializer = new PubSubSerializer()

  test("toBinary & fromBinary for PubSubMsg") {
    val expected = PubSubMsg(SerializedMsg(1, "manifest", ByteVector(3, 4)), 2L)
    val actual = toAndFromBinary(expected)
    actual.timestamp shouldEqual expected.timestamp
    actual.serializedMsg.identifier shouldEqual expected.serializedMsg.identifier
    actual.serializedMsg.manifest shouldEqual expected.serializedMsg.manifest
    actual.serializedMsg.bytes.toArray.toList shouldEqual expected.serializedMsg.bytes.toArray.toList
  }

  test("manifest for PubSubMsg") {
    val msg = PubSubMsg(SerializedMsg(1, "manifest", ByteVector(3, 4)), 2L)
    serializer.manifest(msg) shouldEqual "A"
  }

  def toAndFromBinary[A <: AnyRef](msg: A): A = {
    val manifest = serializer.manifest(msg)
    val bytes = serializer.toBinary(msg)
    val deserialized = serializer.fromBinary(bytes, manifest)
    deserialized.asInstanceOf[A]
  }
}
