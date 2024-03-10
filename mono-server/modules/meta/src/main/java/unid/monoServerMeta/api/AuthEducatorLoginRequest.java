package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
@Schema(title = "data", description = "响应数据", accessMode = Schema.AccessMode.READ_ONLY)
public class AuthEducatorLoginRequest extends AuthLoginRequest{
}
