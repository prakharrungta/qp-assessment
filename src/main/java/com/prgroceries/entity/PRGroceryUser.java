package com.prgroceries.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "users")
@Entity
public class PRGroceryUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539918773323709510L;

	@Id
	private String username;
	private String password;
	private String roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] rolesArr = roles.split(",");
		List<GrantedAuthority> authorities = new ArrayList<>(rolesArr.length);
		for(String role: rolesArr) {
			role = role.trim();
			if(!role.startsWith("ROLE_"))
				role = "ROLE_".concat(role);
			authorities.add(new SimpleGrantedAuthority(role));
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
		return true;
	}

}
