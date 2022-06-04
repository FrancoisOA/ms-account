package pe.com.bootcamp.microservice.account.entity;
  
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "tb_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
	@Id
	private String id;
	private String typeAccount; //tipo de cuenta
	private Double minOpeningAmount; //monto minimo de apertura
	private Double profileAccount; //personal- empresarial
	private String numAccount; //numero de cuenta
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openingDate; //fecha apertura
	private String idCustomer; //cliente
	private Boolean status; //estado	
	private Double currentAvailableBalance; //saldo actual disponible
	private String currency;
	private String numCreditCard;
}