package no.nav.nais.mot.bmot009

import org.apache.camel.spring.SpringRouteBuilder
import org.springframework.stereotype.Component

@Component
class VaskelisteOsloKommuneRoute : SpringRouteBuilder() {

    override fun configure() {
        from("sftp://admin@127.0.0.1:2222/input?move=arkiv&privateKeyFile=src/test/resources/ssh_client_rsa_test_key")
                .log("after from:  ${body()}")
                .process(SocialSecurityProcessor())
                .log("after processing:  ${body()}")
                .to("sftp://admin@127.0.0.1:2222/input/output?privateKeyFile=src/test/resources/ssh_client_rsa_test_key")
                .end()

    }
}