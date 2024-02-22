package data;

import java.time.LocalDateTime;

public class UserToTimestampPair {
    public User user;
    public LocalDateTime timestamp;
    public UserToTimestampPair(User user, LocalDateTime timestamp){
        this.user = user;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
