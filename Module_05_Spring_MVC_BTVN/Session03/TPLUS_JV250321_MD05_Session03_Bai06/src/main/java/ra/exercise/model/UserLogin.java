package ra.exercise.model;

public class UserLogin {
    private String username;
    private String password;
    private boolean isLogin;

    public UserLogin() {
    }

    public UserLogin(String username, String password, boolean isLogin) {
        this.username = username;
        this.password = password;
        this.isLogin = isLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
