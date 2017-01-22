//http://docs.groovy-lang.org/latest/html/documentation/#_expando

class SomeService{
	def SomeOtherService
// 	SomeOtherService someOtherService

	def doSomething(String s){
		if(someOtherService.runCheck()){
			println "Doing $s"
		}
	}
}

class SomeOtherService{
	def runCheck(){
		return false
	}
}

def service = new SomeService()
// service.someOtherService = new Expando()
// service.someOtherService.runCheck = {true}

//service.someOtherService.anotherMethod = {String s -> println "A differnt $s"}
//service.someOtherService.anotherProp = "Another this will work too!"

//someService.anotherMethod('This will work.')
//println someService.anotherProp

// service.someOtherService = [runCheck: {true}] as SomeOtherService
service.doSomething("programming")