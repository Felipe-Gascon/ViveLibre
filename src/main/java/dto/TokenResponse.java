package dto;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor   
public class TokenResponse {
    private String authVivelibreToken;
    private String date;

    public TokenResponse(String authVivelibreToken, String date) {
        this.authVivelibreToken = authVivelibreToken;
        this.date = date;
    }

  
}
 