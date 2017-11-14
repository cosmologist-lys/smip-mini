package com.smip.jwt;

import com.smip.entity.sys.Secuser;
import com.smip.repository.sys.SecuserRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Created by kepler@gmail.com on 2017/11/14.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SecuserRepository secuserRepository;

    public UserDetailsServiceImpl(SecuserRepository secuserRepository) {
        this.secuserRepository = secuserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Secuser user = new Secuser();
        user.setUserName(username);
        Example<Secuser> ex = Example.of(user);
        Secuser myUser = secuserRepository.findOne(ex);
        if(myUser == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(myUser.getUserName(), myUser.getPassWord(), emptyList());
    }

}
