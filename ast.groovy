//http://docs.groovy-lang.org/latest/html/documentation/#_compile_time_metaprogramming
//Going to use the Groovy Console to inspect the AST.
// import groovy.transform.ToString
 
// @ToString(includeNames=true)
class Person{
	String firstName
	String lastName
	String title
}

def person = new Person(firstName:'Tucker', lastName: 'Pelletier', title: 'Senior Software Engineer')
println person