package pwh.coreJooqLibs.converter;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
public class EncryptionIgnoreCaseConverter extends EncryptionConverter {
    private final IgnoreCaseConverter ignoreCaseConvertor = new IgnoreCaseConverter();

    public EncryptionIgnoreCaseConverter(
            @NotBlank String encryptionKey,
            @NotBlank String salt,
            String algo
    ) {
        super(encryptionKey, salt, algo);
    }

    @Override
    public String to(String s) {
        return super.to(ignoreCaseConvertor.to(s));
    }
}
