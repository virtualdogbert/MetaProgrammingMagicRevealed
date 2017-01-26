//http://docs.groovy-lang.org/latest/html/api/groovy/lang/MetaClass.html
def someService = new Object()

// someService.notAMethod() //This gives you an idea why we can ge missingMethod Exceptions
// someService.notAProperty
someService.metaClass.aMethod = {String s -> println s}
//  someService.metaClass.static.aStaticMethod = {String s -> println s + ' statically!'}
  someService.metaClass.aProp = "This will work too!"

// someService.aMethod('This will work.')
// someService.aStaticMethod('This will work')
// println someService.aProp

// String.metaClass.encrypt = {
//  "*** $delegate ***"
// }

// println "test".encrypt()

//someService.metaClass.methods.each{println it}
//someService.metaClass.metaMethods.each{println it}
//someService.metaClass.getProperties().each{println it.getName()}
//someService.metaClass.metaPropertyValues.each{println it.name}
// println someService.metaClass.hasProperty('solid')
// println someService.metaClass.hasProperty('class')
// println someService.metaClass.hasProperty('aProp')
// println someService.metaClass.respondsTo('aMethod')
// println someService.metaClass.getMetaMethod('aMethod')
// println someService.metaClass.respondsTo('toString')
// println someService.metaClass.respondsTo('bar')
