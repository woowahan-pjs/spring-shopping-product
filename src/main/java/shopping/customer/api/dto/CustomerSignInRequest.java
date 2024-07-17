package shopping.customer.api.dto;

public class CustomerSignInRequest {
    private String email;
    private String password;

    public CustomerSignInRequest() {
    }

    public CustomerSignInRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}