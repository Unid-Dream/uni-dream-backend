package unid.monoServerMeta.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class StudentTransactionResponse {
    private UUID id;
}
