package no.nav.nais.mot.bmot009

data class Person(val firstName: String = "X", val lastName: String = "Y" , val age: Int = 0) {



    fun toFixedRecordFormat() : String = String.format("%20s%-30s%025d\n", firstName, lastName, age)
}