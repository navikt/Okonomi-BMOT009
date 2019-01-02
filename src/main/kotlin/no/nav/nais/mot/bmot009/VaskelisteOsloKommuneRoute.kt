package no.nav.nais.mot.bmot009

import org.apache.camel.spring.SpringRouteBuilder
import org.springframework.stereotype.Component

@Component
class VaskelisteOsloKommuneRoute : SpringRouteBuilder() {

    override fun configure() {
        from("sftp://admin@127.0.0.1:4696/?password=admin&useUserKnownHostsFile=false")
                .log("after from:  ${body()}")
                .process(SocialSecurityProcessor())
                .log("after processing:  ${body()}")
                .to("sftp://admin@127.0.0.1:4696/output?password=admin&useUserKnownHostsFile=false")
                .end()

    }
}