package ui

import java.net.ServerSocket


fun freePort(): Int {
    return ServerSocket(0).use { it.localPort }
}