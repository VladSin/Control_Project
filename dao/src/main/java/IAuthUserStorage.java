public interface IAuthUserStorage {

    AuthorizationUser getLogin(String login);
    Long saveAuthUser(AuthorizationUser user);
}
