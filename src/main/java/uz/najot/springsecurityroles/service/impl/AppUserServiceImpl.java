package uz.najot.springsecurityroles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.najot.springsecurityroles.message.ResMessage;
import uz.najot.springsecurityroles.model.AppUser;
import uz.najot.springsecurityroles.security.MyAppSecurityConfig;
import uz.najot.springsecurityroles.service.AppUserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@ComponentScan
public class AppUserServiceImpl implements AppUserService, RowMapper<AppUser> {
//    private final AppUserDao appUserDao;
    private final JdbcTemplate jdbcTemplate;

    private final MyAppSecurityConfig myAppSecurityConfig;

    @Override
    public ResMessage getAll() {
        List<AppUser> appUsers = jdbcTemplate.query("select id, name, username, password, is_active, roles from users " +
                "where is_active<>false", this);
        return ResMessage.getSuccess(appUsers);
    }

    @Override
    public ResMessage getById(Integer id) {
        try {
            AppUser appUser = jdbcTemplate.queryForObject("select id, name, username, password, is_active, roles from users" +
                    " where is_active<>false", this, id);
            return ResMessage.getSuccess(appUser);

        }catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public ResMessage create(AppUser appUser) {
        int update = jdbcTemplate.update("insert into users(name, username, password, role) values(?, ?, ?, ?)",
                appUser.getName(), appUser.getUsername(), myAppSecurityConfig.encoder().encode(appUser.getPassword()), appUser.getRoles());
        if (update > 0){
            return ResMessage.getSuccess();
        }
        return ResMessage.errorMessage(101, "Not saved");
    }

    @Override
    public ResMessage edit(AppUser appUser) {
        int update = jdbcTemplate.update("update users set name=?, username=?, password=?, role=? where id=?",
                appUser.getName(), appUser.getUsername(), myAppSecurityConfig.encoder().encode(appUser.getPassword()), appUser.getRoles(), appUser.getId());
        if (update > 0){
            return ResMessage.getSuccess();
        }
        return ResMessage.errorMessage(102,"Not updated");
    }

    @Override
    public ResMessage remove(Integer id) {
        int update = jdbcTemplate.update("update users set is_active = false where id=?", id);
        if(update>0){
            return ResMessage.getSuccess();
        }
        return ResMessage.errorMessage(103, "Not deleted");
    }
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

}
