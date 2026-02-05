package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("customers")
public class Customer {

    @Id
    private String id;
    private String fullName;
    private String email;
}
