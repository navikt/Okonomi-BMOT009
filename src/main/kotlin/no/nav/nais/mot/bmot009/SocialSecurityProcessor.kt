package no.nav.nais.mot.bmot009

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.camel.Exchange
import org.apache.camel.Processor

class SocialSecurityProcessor: Processor {
    //TODO remove !! ?
    override fun process(exchange: Exchange?) {
        val text : String = exchange!!.getIn().getBody(String::class.java)
        println(text)

        //todo corutine calls to providers
        val personList = text.split("\n")

        val personJob = personList.map { n ->
            GlobalScope.async {
                delay(5000)
                //call 1
                //call 2
                //call 3
                Person(n).toFixedRecordFormat()
            }
        }

        runBlocking {
        val personer = personJob.map { it.await() }
            //todo create propper streamwriter
            exchange.getIn().body = personer.reduce { acc, b -> acc + b  }
        }
    }
}