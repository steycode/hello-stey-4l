package com.stey.hello.api

import akka.util.ByteString
import com.lightbend.lagom.scaladsl.api.deser.MessageSerializer.NegotiatedSerializer
import com.lightbend.lagom.scaladsl.api.deser.MessageSerializer.NegotiatedDeserializer
import com.lightbend.lagom.scaladsl.api.deser.{MessageSerializer, StrictMessageSerializer}
import com.lightbend.lagom.scaladsl.api.transport.DeserializationException
import com.lightbend.lagom.scaladsl.api.transport.MessageProtocol
import com.lightbend.lagom.scaladsl.api.transport.NotAcceptable
import com.lightbend.lagom.scaladsl.api.transport.UnsupportedMediaType

class PlainTextSerializer(val charset: String) extends NegotiatedSerializer[String, ByteString] {
  override val protocol = MessageProtocol(Some("text/plain"), Some(charset))

  def serialize(s: String) = ByteString.fromString(s, charset)
}

class PlainTextDeserializer(val charset: String) extends NegotiatedDeserializer[String, ByteString] {
  def deserialize(bytes: ByteString) =
    bytes.decodeString(charset)
}

class TextMessageSerializer extends StrictMessageSerializer[String] {
  override def serializerForRequest: NegotiatedSerializer[String, ByteString] = new PlainTextSerializer("utf-8")

  override def deserializer(protocol: MessageProtocol): MessageSerializer.NegotiatedDeserializer[String, ByteString] = new PlainTextDeserializer(protocol.charset.getOrElse("utf-8"))

  override def serializerForResponse(acceptedMessageProtocols: Seq[MessageProtocol]): NegotiatedSerializer[String, ByteString] = new PlainTextSerializer("utf-8")
}