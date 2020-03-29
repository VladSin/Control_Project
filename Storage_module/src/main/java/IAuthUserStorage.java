public interface IAuthUserStorage {

    AuthorizationUser getLogin(String login);
    void saveAuthUser(AuthorizationUser user);
}
