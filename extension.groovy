//http://docs.groovy-lang.org/latest/html/documentation/#_extension_modules
//Remeber Groovy caches grabs in /home/userName/.groovy/grapes

@Grab('org.virtualdogbert:stringExtension:0.2')
class testExtension{

	static void main(def args){
        println "test".encrypt()
    }
}