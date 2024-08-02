package com.chutneytesting.hello

import com.chutneytesting.kotlin.synchronize.jsonSerialize
import com.chutneytesting.kotlin.synchronize.synchronise
import com.chutneytesting.kotlin.util.ChutneyServerInfo

val chutneyLocalServer = ChutneyServerInfo(
    url = "https://localhost:8081", user = "admin", password = "admin"
)

fun main() {
    test_suite.forEach {
        it.jsonSerialize("src/main/resources/chutney/in_progress")
        it.synchronise(serverInfo = chutneyLocalServer)
    }
}
