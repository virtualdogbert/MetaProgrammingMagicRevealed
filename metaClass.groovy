//http://docs.groovy-lang.org/latest/html/api/groovy/lang/MetaClass.html
def someService = new Object()

// someService.notAMethod()
//someService.notAProperty
//someService.metaClass.aMethod = {String s -> println s}
// someService.metaClass.static.aStaticMethod = {String s -> println s + ' statically!'}
// someService.metaClass.aProp = "This will work too!"

//someService.aMethod('This will work.')
// someService.aStaticMethod('This will work')
//println someService.aProp

//String.metaClass.encrypt = {
//  "*** $delegate ***"
//}

//"test".encrypt()

//println someService.metaClass.getMethods()
//someService.metaClass.getProperties().each{println it.getName()}
// println someService.metaClass.hasProperty('solid')
// println someService.metaClass.hasProperty('class')
// println someService.metaClass.hasProperty('aProp')
// println someService.metaClass.respondsTo('toString')
// println someService.metaClass.respondsTo('bar')
