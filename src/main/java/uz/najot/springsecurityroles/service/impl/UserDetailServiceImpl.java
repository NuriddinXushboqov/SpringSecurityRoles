package uz.najot.springsecurityroles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.najot.springsecurityroles.model.AppUser;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService, RowMapper<AppUser> {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(rs.getInt(1));
        appUser.setName(rs.getString(2));
        appUser.setUsername(rs.getString(3));
        appUser.setPassword(rs.getString(4));
        appUser.setIsActive(rs.getBoolean(5));
        appUser.setRoles(appUser.getRolesByString(rs.getString(6)));
        return appUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            try {
                AppUser appUser = jdbcTemplate.queryForObject("select id, name, username, password, is_active, roles from users where username=?", this, username);
                return appUser;

            }catch (Exception e){
                throw new UsernameNotFoundException("Username not found");
            }
    }
}
