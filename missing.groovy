//http://docs.groovy-lang.org/latest/html/documentation/#_methodmissing

class Missing extends Expando{

    def methodMissing(String methodName, args = []) throws Exception {
        def methodLookup =[
            add:{x,y -> x + y},
            sub:{x,y -> x - y },
            mul:{x,y -> x * y },
            div:{x,y -> x / y }
        ]
        if(methodName in methodLookup){
            println "Method $methodName added"
            this.metaClass."$methodName" = methodLookup[methodName]
        } else {
            throw new MissingMethodException(methodName, this.class, args)
        }
        
        return this."$methodName"(*args)

    }

    def propertyMissing(String propertyName) throws Exception {
        def propertyLookup = [
            pi:3.1415926535,
            e:2.71828,
            K:2.6854520010,
            A:1.2824271291,
            Life_Universe_Everything: 42
        ]
        if(propertyName in propertyLookup){
            println "Property $propertyName added"
            this.metaClass."$propertyName" = propertyLookup[propertyName]
        } else {
            //throw new MissingPropertyException(propertyName)
            throw new Exception("No such property: $propertyName for class: ${this.getClass().name}")
        }
        return this."$propertyName"
    }

}

def missing = new Missing()

// println missing.add(1,1)
// println missing.add(1,2)
// println missing.pi
// println missing.pi
// println missing.Life_Universe_Everything
// println missing.pa
// println missing.foo(1,2)