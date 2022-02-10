package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login)
            throws UserNotFoundException {
        User res = null;
        for (int i = 0; i < users.length; i++) {
            if (login.equals(users[i].getUsername())) {
                res = users[i];
                break;
            }
        }
        if (res == null) {
            throw new UserNotFoundException("User not found");
        }
        return res;
    }

    public static boolean validate(User user) throws UserInvalidException {
        boolean res = false;
        if (user.isValid() && user.getUsername().length() >= 3) {
            res = true;
        } else {
            throw new UserInvalidException("User is not valid");
        }
        return res;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Pavel Milyutin", true)
        };
        try {
            User user = findUser(users, "Pavel Milyutin");
            if (validate(user)) {
                System.out.println("This user has an access");
        }
        } catch (UserInvalidException exc) {
            exc.printStackTrace();
        } catch (UserNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}
