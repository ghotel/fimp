import java.text.DateFormat
import java.text.MessageFormat

/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 18.12.12
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
class Ifim {

    public static final String VERSION = "0.0.1"

    public static final String CREATE_IMPORT_COMMAND = "-createImport"
    public static final String CREATE_IMPORT_SHORT_COMMAND = "-c"
    public static final String FINISH_IMPORT_COMMAND = "-finishImport"
    public static final String FINISH_IMPORT_SHORT_COMMAND = "-f"
    public static final String ABOUT_COMMAND = "-about"
    public static final String VERSION_COMMAND = "-version"

    public static void main(String[] args) {

        if (args.size() == 0) {
            createImport()
        }

        if (args.contains(ABOUT_COMMAND)){
            printAboutInformation()
        }

        if (args.contains(VERSION_COMMAND)) {
            println("Version: " + VERSION)
        }

        if (args.contains(CREATE_IMPORT_COMMAND) || args.contains(CREATE_IMPORT_SHORT_COMMAND)) {
            createImport()
        }

        if (args.contains(FINISH_IMPORT_COMMAND) || args.contains(FINISH_IMPORT_SHORT_COMMAND)) {
            importFinished()
        }

    }

    static def printAboutInformation() {
        println("The main purpose of this small app is to create an import file used by ifin24 online application.")
        println("It also provides actions for handling the import file.")
        println()
        println("Usage: groovy Ifim.groovy [params]")
        println("\t default execution will call createImport")
        println(MessageFormat.format("\t {0} -> prints this information", ABOUT_COMMAND))
        println(MessageFormat.format("\t {0} -> prints version information", VERSION_COMMAND))
        println(MessageFormat.format("\t {0} or {1} -> creates import file based on default data xml file",
                CREATE_IMPORT_COMMAND, CREATE_IMPORT_SHORT_COMMAND))
        println(MessageFormat.format("\t {0} or {1} -> moves import file to archive folder and changes its name to " +
                "include current date and time", FINISH_IMPORT_COMMAND, FINISH_IMPORT_SHORT_COMMAND))

    }

    static def importFinished() {
        moveInputFileToArchive()
        deleteImportCsv()
        createEmptyDataSourceFile()
    }

    static def deleteImportCsv() {
        File f = new File(TemplateEngine.OUTPUT_FILE)
        f.delete()
    }

    private static void moveInputFileToArchive() {
        String newFileDir = MessageFormat.format("../archive")
        String newFileName = MessageFormat.format("importedTransactions_{0}.txt", new Date().format("yyyy-M-d_HH_mm_ss"))

        File importFile = new File(TemplateEngine.INPUT_FILE)
        if (importFile.exists()) {
            importFile.renameTo(new File(newFileDir, newFileName))
        } else {
            println("File does not exist")
        }
    }

    static def createEmptyDataSourceFile() {
        File f = new File(TemplateEngine.INPUT_FILE)
        f.write("2012-11-23;Transakcja;1,00")
    }

    static def createImport() {
        TemplateEngine.createImportFile()
    }

}
