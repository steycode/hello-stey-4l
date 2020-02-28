package com.stey.hello.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import com.lightbend.lagom.scaladsl.api.deser.MessageSerializer

trait HelloService extends Service {

  def hello(greet: String): ServiceCall[NotUsed, String]
  def nihao(greet: String): ServiceCall[NotUsed, String]

  override def descriptor: Descriptor = {
    import Service._

    named("stey-hello")
      .withCalls(
        pathCall("/hello/:greet", hello _),
        pathCall("/nihao/:greet", nihao _)
        (MessageSerializer.NotUsedMessageSerializer, new TextMessageSerializer)
      )
      .withAutoAcl(true)
  }
}
