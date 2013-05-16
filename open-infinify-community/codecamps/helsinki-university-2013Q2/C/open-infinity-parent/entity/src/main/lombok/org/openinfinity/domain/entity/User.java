package org.openinfinity.domain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.management.relation.Role;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openinfinity.core.annotation.NotScript;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jknihtil
 */
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @NotScript
    private String id;
    @NotScript
    @NotNull
    @NonNull
    private String userName;
    @NotScript
    @NonNull
    private String password;
    private Set<Role> roles = new HashSet<Role>();
}
