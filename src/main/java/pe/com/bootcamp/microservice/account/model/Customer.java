package pe.com.bootcamp.microservice.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;   
    private String name;
    private String typeCustomer;  //Persona - Empresarial
    private String documentNumber;  //Dni - Ruc
    private String profile;  //VIP PYME (NUEVO)
}
