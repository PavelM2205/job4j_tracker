package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login)
            throws UserNotFoundException {
        User res = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                res = user;
                break;
            }
        }
        if (res == null) {
            throw new UserNotFoundException("User not found");
        }
        return res;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("User is not valid");
        }
        return true;
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
        } catch (UserInvalidException ui) {
            ui.printStackTrace();
        } catch (UserNotFoundException unf) {
            unf.printStackTrace();
        }
    }
}
