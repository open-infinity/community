package org.openinfinity.domain.entity;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openinfinity.core.annotation.NotScript;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Jarno Knihtilä
 */
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Id
    @NotScript
    private String id;
    @NotScript
    @NotNull
    @NonNull
    private String name;
}
