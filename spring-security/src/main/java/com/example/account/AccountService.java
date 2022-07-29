package com.example.account;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * username을 받아와서 데이터에서 가져와서 UserDetails type으로 반환해야함
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserAccount(account);
    }

    public Account createNew(Account account) {

        account.encodePassword(passwordEncoder);
        return accountRepository.save(account);
    }
}
