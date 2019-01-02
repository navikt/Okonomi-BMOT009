package no.nav.nais.mot.bmot009

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.TimeUnit

@RunWith(SpringRunner::class)
@SpringBootTest
class Bmot009ApplicationTests {

    @Before
    fun setup(){
        val sftp = SFTPServerConfig().configure("127.0.0.1",4696, "input")
        sftp.start()
    }

    @Test
    fun contextLoads() {
        TimeUnit.SECONDS.sleep(10L)
    }

}

