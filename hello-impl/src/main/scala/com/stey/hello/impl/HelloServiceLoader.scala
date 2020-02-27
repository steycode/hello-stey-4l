package com.stey.hello.impl

import com.lightbend.lagom.scaladsl.api.Descriptor
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import com.stey.hello.api.HelloService
import play.api.libs.ws.ahc.AhcWSComponents

class HelloServiceLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication = {
    new HelloApplication(context) with LagomDevModeComponents
  }

  override def describeService: Some[Descriptor] = {
    Some(readDescriptor[HelloService])
  }
}

abstract class HelloApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  override lazy val lagomServer: LagomServer = serverFor[HelloService](wire[HelloServiceImpl])
}
