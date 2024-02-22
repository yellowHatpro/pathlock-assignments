package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.User;
import data.UserToTimestampPair;
import utils.Utils;

import java.io.*;

import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.LocalDateTime;
import java.util.*;

import static java.net.http.HttpResponse.*;

public class ApiCallSimulationImpl  implements ApiCallSimulation {

    TreeMap<String, UserToTimestampPair> users;
    HttpClient client;
    Gson gson;



    public ApiCallSimulationImpl() {
        users = new TreeMap<>();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    @Override
    public List<User> getUsersFromApi() {
        try {
            URI url = new URI("https://jsonplaceholder.typicode.com/users");
            HttpResponse<String> response = client.send(
                    HttpRequest
                            .newBuilder(url)
                            .header("Content-Type", "application/json")
                            .GET()
                            .build(),
                    BodyHandlers.ofString()
            );
            System.out.println(response.statusCode());
            return convertJsonToUsers(response.body());
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Some problem occurred, " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Some IO problem occurred");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Error");
        }
        return null;
    }

    @Override
    public User getUserFromApi(String username) {
        if (users.containsKey(username)){
            User user = users.get(username).user;
            System.out.println("Fetching "+username+" from cache");
            if (Utils.isSameHour(LocalDateTime.now(),users.get(username).timestamp)){
                return user;
            }
        }
        try {
            URI url = new URI("https://jsonplaceholder.typicode.com/users?username=" + username);
            HttpResponse<String> response = client.send(
                    HttpRequest
                            .newBuilder(url)
                            .header("Content-Type", "application/json")
                            .GET()
                            .build(),
                    BodyHandlers.ofString()
            );
            System.out.println(response.statusCode());
            User user =  convertJsonToUser(response.body());
            storeUser(user);
            return user;
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Some problem occurred, " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Some IO problem occurred");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Error");
        }
        return null;
    }

    @Override
    public List<User> convertJsonToUsers(String data) {
        Type listType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @Override
    public User convertJsonToUser(String data) {
        String user = data.substring(1,data.length()-1);
        return gson.fromJson(user, User.class);
    }

    @Override
    public User searchUser(String username) {
        if (!users.containsKey(username)){
            System.out.println("Sorry, username does not exist");
            return null;
        }
        return users.get(username).getUser();
    }

    @Override
    public void storeUser(User user) {
        UserToTimestampPair userToTimestampPair = new UserToTimestampPair(user, LocalDateTime.now());
        users.put(user.getUsername(), userToTimestampPair);
    }

    @Override
    public void storeUsers(List<User> userList) {
        for(User user: userList){
            users.put(user.getUsername(),
                    new UserToTimestampPair(user, LocalDateTime.now()));
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>(Collections.emptyList());
        for(Map.Entry<String, UserToTimestampPair> userEntry: users.entrySet()){
            userList.add(userEntry.getValue().getUser());
        }
        return userList;
    }
}
