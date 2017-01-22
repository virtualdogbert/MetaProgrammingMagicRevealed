//http://docs.groovy-lang.org/latest/html/documentation/#_delegation_strategy
//http://docs.groovy-lang.org/latest/html/documentation/#TheDelegatesToannotation-DelegatesTo
class PoorMansDSL{ 
	def walk(int length, String unit){
		println "I walked $length ${unit}."
	}


	def runCommand(@DelegatesTo(PoorMansDSL)Closure c){
		c.delegate = this
		c()
	}
}

def dsl = new PoorMansDSL()
dsl.runCommand({println "I ran."})
dsl.runCommand({walk 5, "feet"})


