package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса.
 * @author Pavel Milutin.
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных о пользователях и их аккаунтах осуществляется в коллекции
     * типа Map.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет его в хранилище.
     * При добавлении пользователя для него создается пустой список
     * счетов типа ArrayList.
     * @param user пользователь, который добавляется в общую базу.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавляет новый счет пользователю.
     * @param passport номер паспорта. Используется для идентификации пользователя.
     * @param account счет, который необходимо добавить пользователю.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод осуществляет поиск пользователя по номеру паспорта.
     * @param passport номер паспорта
     * @return возвращает пользователя или null, если пользователь отсутствует в базе.
     */
    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Метод осуществляет поиск счета пользователя по реквизитам и номеру паспорта.
     * @param passport номер паспорта пользователя
     * @param requisite реквизиты счета
     * @return возвращает счет пользователя
     */
    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод реализует перевод средств между счетами пользователей.
     * @param srcPassport номер паспорта пользователя, со счета которого будет осуществляться
     *                    перевод средств
     * @param srcRequisite реквизиты счета пользователя, со счета которого будет осуществляться
     *                     перевод средств
     * @param destPassport номер паспорта пользователя, в адрес которого будут переведены средства
     * @param destRequisite реквизиты счета пользователя, в адрес которого будут переведены средства
     * @param amount сумма перевода
     * @return возвращает true в случае удачного осуществления перевода, либо false если не были найдены
     * пользователь и/или счет
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
            String destPassport, String destRequisite, double amount) {
        boolean result = false;
            Account acSource = findByRequisite(srcPassport, srcRequisite);
            Account acDestination = findByRequisite(destPassport, destRequisite);
            if (acSource != null && acDestination != null
                    && acSource.getBalance() >= amount) {
                acSource.setBalance(acSource.getBalance() - amount);
                acDestination.setBalance(acDestination.getBalance() + amount);
                result = true;
            }
        return result;
    }
}


