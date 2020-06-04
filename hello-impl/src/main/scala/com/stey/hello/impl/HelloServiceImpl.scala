package com.stey.hello.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.stey.hello.api.HelloService

import scala.concurrent.Future

class HelloServiceImpl extends HelloService {

  override def hello(greet: String): ServiceCall[NotUsed, String] = {
    ServiceCall { _ =>
      Future.successful(s"Hello $greet")
    }
  }

  override def helloChina(message: String): ServiceCall[NotUsed, String] = {
    ServiceCall { _ =>
      Future.successful(s"你好 $message")
    }
  }
}
