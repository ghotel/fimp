
/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 04.12.12
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
import groovy.text.SimpleTemplateEngine

class TemplateEngine {

    static final String TEMPLATE_PATH = "../templates/mbank_import.tmpl"
    static final String INPUT_FILE = "../data/transactions.txt"
    static final String OUTPUT_FILE = "../data/import_file.csv"

    public static void main(String[] args) {

        def trans = TransactionsReader.readFile(INPUT_FILE)

        def templateFile = new File(TEMPLATE_PATH)

        def binding = [transactions : trans]
        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(templateFile).make(binding)

        writeToFile(template.toString())
        println template.toString()
    }

    static def createImportFile(){
        def trans = TransactionsReader.readFile(INPUT_FILE)

        def templateFile = new File(TEMPLATE_PATH)

        def binding = [transactions : trans]
        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(templateFile).make(binding)

        writeToFile(template.toString())

        println("Import file created and saved to " + OUTPUT_FILE)
    }

    private static void writeToFile(String fileContent){
       new File(OUTPUT_FILE).write(fileContent)
    }
}