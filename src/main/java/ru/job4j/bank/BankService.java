package ru.job4j.bank;

import java.util.*;

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
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
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
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод осуществляет поиск счета пользователя по реквизитам и номеру паспорта.
     * @param passport номер паспорта пользователя
     * @param requisite реквизиты счета
     * @return возвращает счет пользователя
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        Optional<Account> result = Optional.empty();
        if (user.isPresent()) {
            result = users.get(user.get())
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
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
            Optional<Account> acSource = findByRequisite(srcPassport, srcRequisite);
            Optional<Account> acDestination = findByRequisite(destPassport, destRequisite);
            if (acSource.isPresent() && acDestination.isPresent()
                    && acSource.get().getBalance() >= amount) {
                acSource.get().setBalance(acSource.get().getBalance() - amount);
                acDestination.get().setBalance(acDestination.get().getBalance() + amount);
                result = true;
            }
        return result;
    }
}


