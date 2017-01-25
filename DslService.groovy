//http://melix.github.io/blog/2015/03/sandboxing.html

@Grab('de.sven-jacobs:loremipsum:1.0')
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.expr.CastExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.IfStatement
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer
import static org.codehaus.groovy.syntax.Types.*

/**
 * This is a sample DSL service
 */

class DslService implements Dgenerator {

    private GroovyShell shell = null


    private List<Integer> tokensWhiteList = [
            LOGICAL_AND,
            LOGICAL_OR,
            NOT,
            PLUS,
            MINUS,
            MULTIPLY,
            DIVIDE,
            MOD,
            POWER,
            MINUS_MINUS,
            COMPARE_EQUAL,
            COMPARE_NOT_EQUAL,
            COMPARE_LESS_THAN,
            COMPARE_LESS_THAN_EQUAL,
            COMPARE_GREATER_THAN,
            COMPARE_GREATER_THAN_EQUAL,
    ].asImmutable()

    private List<Class> constantTypesClassesWhiteList = [
            Object,
            String,
            Integer,
            Float,
            Long,
            Double,
            BigDecimal,
            Boolean,
            Integer.TYPE,
            Long.TYPE,
            Float.TYPE,
            Double.TYPE,
            Boolean.TYPE
    ].asImmutable()

    private List <Class> receiversClassesWhiteList = [
            Object,
            Closure,
            Math,
            Integer,
            Float,
            Double,
            Long,
            BigDecimal
    ].asImmutable()

    /**
     * Sets up the groovy shell and applies the SecureASTCustomizer security from the white lists above.
     */
    DslService() throws Exception {

        final SecureASTCustomizer secure = new SecureASTCustomizer()

        secure.with {
            closuresAllowed = true
            methodDefinitionAllowed = false
            indirectImportCheckEnabled = false
            importsWhitelist = ['org.springframework.beans.factory.annotation.Autowired']
            staticImportsWhitelist = []
            staticStarImportsWhitelist = []
            statementsWhitelist = [IfStatement, BlockStatement, ExpressionStatement]
            tokensWhitelist = this.tokensWhiteList
            constantTypesClassesWhiteList = this.constantTypesClassesWhiteList
            receiversClassesWhiteList = this.receiversClassesWhiteList
            expressionsBlacklist = [CastExpression]
        }

        CompilerConfiguration config = new CompilerConfiguration()
        config.addCompilationCustomizers(secure)
        shell = new GroovyShell(this.class.classLoader, new Binding(), config)
    }

    /**
     * 
     */
    def methodMissing(String methodName, args = []) throws Exception {
        String returnValue = ''
        String command = ''
        List argsStack = []
        String currentArg = ''
        boolean inCommand = true
        boolean inArgs = false
        boolean inArg = false
        
        methodName[1..-2].each{
            if(inCommand && it != '(' && it != ')'){
                if(command){
                    if(this.metaClass.respondsTo(this, command)){
                        returnValue += "$command"()
                        command = ''
                    } else {
                        throw new MissingMethodException(command, this.class, argsStack)
                    }
                    
                }
                command = it
                return
            }
            
            if(it == '('){
                inCommand = false
                inArgs = true
                return
            }
            
            if(inArgs && it != ',' && it != ')'){
                currentArg += it
                return
            }
            
            if(inArgs && it == ','  && it != ')'){
                argsStack << currentArg
                currentArg = ''
                return
            }
            
            if(inArgs && it == ')'){
                inCommand = true
                inArgs = false
                if(currentArg){
                    argsStack << currentArg
                }
                
                if(this.metaClass.respondsTo(this, command)){
                    if(argsStack){
                        returnValue += "$command"(*argsStack)
                    } else {
                        returnValue += "$command"()
                    }
                } else {
                    throw new MissingMethodException(command, this.class, argsStack)
                }
                currentArg = ''
                command = ''
                argsStack = []
                return
            }
            
        }
        
        if(command){
            if(this.metaClass.respondsTo(this, command) || this.metaClass.respondsTo(this, "$command")){
                returnValue += "$command"()
            } else {
                throw new MissingMethodException(command, this.class)
            }
        }

        returnValue
    }


    def propertyMissing(String name) throws Exception {
        println name
        name.each{
            execute(it)
        }
    }

    def bandMethods = ['run(', 'execute(', 'propertyMissing(','methodMissing(']



    def execute(String command) throws Exception {
        bandMethods.each { String bandMethod ->
            if (command.contains(bandMethod)) {
                throw new SecurityException("The method $bandMethod is not allowed.")
            }
        }

        Closure c = shell.evaluate("""{->"$command"()}""")
        c.delegate = this
        c.resolveStrategry = Closure.DELEGATE_ONLY
        c.call()
    
    }

    static void main(def args){
        def dsl = new DslService()
        println dsl.execute('vV')
        println dsl.execute('s')
        println dsl.execute('m')
        println dsl.execute('LlQCcBA')
        println dsl.execute('p')
        println dsl.execute('d')
        println dsl.execute('t')
        println dsl.execute('I')
        println dsl.execute('D')
        println dsl.execute('X')
        println dsl.execute('x')
        println dsl.execute('w')
        println dsl.execute('P')
    }
}

