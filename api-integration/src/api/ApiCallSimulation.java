package api;

import data.User;

import java.util.List;

public interface ApiCallSimulation {
    List<User> getUsersFromApi();
    User getUserFromApi(String username);
    List<User> convertJsonToUsers(String data);
    User convertJsonToUser(String data);
    User searchUser(String username);
    void storeUser(User user);
    void storeUsers(List<User>users);
    List<User> getUsers();
}
