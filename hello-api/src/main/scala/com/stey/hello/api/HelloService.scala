package com.stey.hello.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait HelloService extends Service {

  def hello(greet: String): ServiceCall[NotUsed, String]

  def helloChina(greet: String): ServiceCall[NotUsed, String]

  override def descriptor: Descriptor = {
    import Service._

    named("stey-hello")
      .withCalls(
        pathCall("/hello/:greet", hello _),
        pathCall("/helloChina/:message", helloChina _)
      )
      .withAutoAcl(true)
  }
}
