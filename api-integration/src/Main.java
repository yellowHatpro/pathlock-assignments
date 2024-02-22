import api.ApiCallSimulation;
import api.ApiCallSimulationImpl;
import data.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApiCallSimulationImpl acs = new ApiCallSimulationImpl();
        simulateApiCall(acs);
    }

    public static void simulateApiCall(ApiCallSimulation acs) {
        User user = acs.getUserFromApi("Antonette");
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getName());
        System.out.println("\n");
        // Caching is done
        User user2 = acs.getUserFromApi("Antonette");
        System.out.println(user2.getUsername());
        User user3 = acs.getUserFromApi("Antonette");
        System.out.println(user3.getUsername());
        System.out.println("\n");
        //Search
        System.out.println("Search Results: ");
        User searchUser = acs.searchUser("Antonette");
        System.out.println(searchUser.getUsername());
        System.out.println(searchUser.getName());
        System.out.println(searchUser.getEmail());
        System.out.println(searchUser.getPhone());
        System.out.println("Search result ends\n");
        List<User> usersList= acs.getUsersFromApi();
        acs.storeUsers(usersList);
        //Query all the users, data is stored in sorted order with respect to username
        List<User> userList = acs.getUsers();
        for (User usr: userList){
            System.out.println(usr.getUsername());
        }
    }
}