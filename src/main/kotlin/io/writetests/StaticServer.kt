package io.writetests

import spark.Spark
import spark.Spark.awaitInitialization
import spark.Spark.init
import spark.kotlin.port
import spark.kotlin.staticFiles


class StaticServer(val port: Int) {

    fun start(): StaticServer {
        staticFiles.location("/")
        port(port)
        init()
        return this
    }

    fun waitForStartUp(): StaticServer {
        awaitInitialization()
        return this
    }

    fun stop() {
        Spark.stop()
    }

}