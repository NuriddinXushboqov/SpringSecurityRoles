package uz.najot.springsecurityroles.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class AppUser implements UserDetails{
    private Integer id;
    private String name;
    private String username;
    private String password;
    private List<String> roles;
    private Boolean isActive;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role:roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public List<String> getRolesByString(String string) {
        String[] split = string.split("\\,");
        List<String> list = new ArrayList<>();
        for (String s:split) {
            list.add(s.trim().toUpperCase());
        }
        return list;
    }

}
