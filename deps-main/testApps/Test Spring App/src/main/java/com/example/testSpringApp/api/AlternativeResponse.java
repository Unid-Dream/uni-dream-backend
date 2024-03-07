package com.example.testSpringApp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlternativeResponse<DataBody> {
    @NotNull
    @Schema(description = "Request Timestamp in epoch time")
    private Long requestedTime;
    @Schema(description = "Error Text")
    private String errorText;
    @Schema(description = "Response Data")
    @Valid
    private DataBody body;

    public static <DataBody> AlternativeResponse<DataBody> of(DataBody data) {
        return AlternativeResponse.<DataBody>builder().body(data).build();
    }
}
