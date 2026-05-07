package com.works.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class IdentityInfo {

    private int socialSecurityNumber;
    private String secretName;

}
