
/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 04.12.12
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
import groovy.text.Template
import groovy.text.SimpleTemplateEngine

class TemplateEngine {

    static void main(String[] args) {

        def trans = TransactionsReader.readFile("data/sandbox.txt")

        def templateFile = new File("properties/mbank_import.tmpl")
//
//        def trans1 = new Transaction("Zakupy", new Date(), new BigDecimal("10.12"))
//        def trans2 = new Transaction("Zakupy inne", new Date(), new BigDecimal("11.12"))
//
        def binding = [dupa: "Jakas dupa", transactions : trans]
        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(templateFile).make(binding)
        println template.toString()
    }
}