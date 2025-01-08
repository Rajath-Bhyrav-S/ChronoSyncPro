//package com.cryptosoft.config;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.cryptosoft.entity.UserAuth;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class CustomUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//
//	private final UserAuth userAuth;
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority(userAuth.getRole().toString()));
//	}
//
//	@Override
//	public String getPassword() {
//		return userAuth.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return userAuth.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
//package com.cryptosoft.config;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//    private String username;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
//package com.cryptosoft.config;
//
//import java.util.Collection;
//
//
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.cryptosoft.entity.UserAuth;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class CustomUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//
//	private final UserAuth userAuth;
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority(userAuth.getRole().toString()));
//	}
//
//	@Override
//	public String getPassword() {
//		return userAuth.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return userAuth.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
package com.cloud.ChronoSyncPro.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cloud.ChronoSyncPro.entity.UserAuth;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final UserAuth userAuth;

    public CustomUserDetails(UserAuth userAuth) {
        this.userAuth = userAuth;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userAuth.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return userAuth.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuth.getEmail();
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
