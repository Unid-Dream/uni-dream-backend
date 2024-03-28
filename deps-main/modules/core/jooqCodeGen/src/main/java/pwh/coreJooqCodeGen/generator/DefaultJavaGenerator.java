package pwh.coreJooqCodeGen.generator;

import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class DefaultJavaGenerator extends JavaGenerator {
    @Override
    protected void printClassAnnotations(JavaWriter out, Definition definition, GeneratorStrategy.Mode mode) {
        super.printClassAnnotations(out, definition, mode);
        if (Set.of(GeneratorStrategy.Mode.POJO, GeneratorStrategy.Mode.RECORD).contains(mode)) {
            out.println("@lombok.experimental.FieldNameConstants(innerTypeName = \"Columns\")");
        }
    }



}
