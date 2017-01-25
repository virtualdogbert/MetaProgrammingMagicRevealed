//http://docs.groovy-lang.org/latest/html/documentation/#_invokemethod
//Careful about mixing, matching, and what you do in missingMethod, missingProperty, invokeMethod. Can lead to stackoverflow.
class InvokedTheMethod  implements GroovyInterceptable{
	def invokeMethod(String name, Object args) {
        System.out.println "calling $name with args: $args"
        def returnValue = metaClass.getMetaMethod(name, args).invoke(this, args)
        System.out.println "result before return $returnValue"
        System.out.println "$name was called with args: $args"
        return returnValue
    }

    def test() {
        return 'method exists'
    }
}

def i = new InvokedTheMethod()
println i.test()

