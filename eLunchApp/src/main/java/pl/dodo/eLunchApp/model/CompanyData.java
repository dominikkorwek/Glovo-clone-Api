package pl.dodo.eLunchApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Embeddable
@Data
public class CompanyData {

    @NotNull
    @Column(name = "companyName")
    private String name;

    @Embedded
    @NotNull
    private Address address;

    @NotNull
    private String NIP;

    @NotNull
    private String regon;

    @NotNull
    private String phone;

    @NotNull
    @Email
    private String email;
}
