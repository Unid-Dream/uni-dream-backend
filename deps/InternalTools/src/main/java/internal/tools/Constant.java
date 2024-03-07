package internal.tools;

public class Constant {
    public static final String PLACEHOLDER_VAR_PREFIX = "X";
    public static final String PLACEHOLDER_INHERIT_GROUP_ID = "inherit";

    // FILES & DIRS
    public static final String PROJECT_ROOT_DIR = System.getProperty("user.dir");
    public static final String MAVEN_POM_FILE = "pom.xml";
    public static final String CONFIG_FILE = "generator.json";
    public static final String TEMPLATE_DIR = String.format("%s/.templates", PROJECT_ROOT_DIR);
    public static final String INTERNAL_TOOL_DIR = String.format("%s/InternalTools", PROJECT_ROOT_DIR);
}
