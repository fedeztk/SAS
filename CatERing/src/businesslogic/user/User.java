package businesslogic.user;

import javafx.collections.FXCollections;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class User {

    private static Map<Integer, User> loadedUsers = FXCollections.observableHashMap();

    public static enum Role {SERVIZIO, CUOCO, CHEF, ORGANIZZATORE};

    private int id;
    private String username;
    private HashMap<Role, Behaviour> currentBehaviours;

    public User() {
        id = 0;
        username = "";
        currentBehaviours = new HashMap<>();
    }

    public Behaviour useBehaviour(Role r) {
        return currentBehaviours.get(r);
    }


    public boolean isChef() {
        return currentBehaviours.containsKey(Role.CHEF);
    }

    public String getUserName() {
        return username;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        String result = username;
        if (currentBehaviours.size() > 0) {
            result += ": ";

            for (User.Role r : currentBehaviours.keySet()) {
                result += r.toString() + " ";
            }
        }
        return result;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static User loadUserById(int uid) {
        if (loadedUsers.containsKey(uid)) return loadedUsers.get(uid);

        User load = new User();
        String userQuery = "SELECT * FROM Users WHERE id='" + uid + "'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                load.id = rs.getInt("id");
                load.username = rs.getString("username");
            }
        });
        if (load.id > 0) {
            loadedUsers.put(load.id, load);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + load.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            load.currentBehaviours.put(User.Role.CUOCO, new Cook());
                            break;
                        case 'h':
                            load.currentBehaviours.put(User.Role.CHEF, new Chef());
                            break;
                        case 'o':
                            load.currentBehaviours.put(User.Role.ORGANIZZATORE, new Organizer());
                            break;
                        case 's':
                            load.currentBehaviours.put(User.Role.SERVIZIO, new Service());
                    }
                }
            });
        }
        return load;
    }

    public static User loadUser(String username) {
        User u = new User();
        String userQuery = "SELECT * FROM Users WHERE username='" + username + "'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
            }
        });
        if (u.id > 0) {
            loadedUsers.put(u.id, u);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + u.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            u.currentBehaviours.put(User.Role.CUOCO, new Cook());
                            break;
                        case 'h':
                            u.currentBehaviours.put(User.Role.CHEF, new Chef());
                            break;
                        case 'o':
                            u.currentBehaviours.put(User.Role.ORGANIZZATORE, new Organizer());
                            break;
                        case 's':
                            u.currentBehaviours.put(User.Role.SERVIZIO, new Service());
                    }
                }
            });
        }
        return u;
    }
}

interface Behaviour {
}


class Organizer implements Behaviour {

}

class Service implements Behaviour {

}

class Chef implements Behaviour {


}


