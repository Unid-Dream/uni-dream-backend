package pwh.springWebStarter.filter;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Override Spring defaults
 */
public class AutoConfigFilter implements AutoConfigurationImportFilter {
    public final List<String> shouldSkip = new ArrayList<>() {{
        add(ErrorMvcAutoConfiguration.class.getName());
    }};

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata metadata) {
        boolean[] matches = new boolean[autoConfigurationClasses.length];
        for (int i = 0; i < autoConfigurationClasses.length; i++) {
            matches[i] = !shouldSkip.contains(autoConfigurationClasses[i]);
        }
        return matches;
    }
}
