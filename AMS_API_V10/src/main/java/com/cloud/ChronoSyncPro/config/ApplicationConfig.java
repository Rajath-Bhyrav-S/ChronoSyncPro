////package com.cryptosoft.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
////import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////
////import com.cryptosoft.entity.Role;
////import com.cryptosoft.entity.UserAuth;
////import com.cryptosoft.repository.UserAuthRepository;
////
////import lombok.RequiredArgsConstructor;
////
////@Configuration
////@RequiredArgsConstructor
////public class ApplicationConfig {
////
////	private final UserAuthRepository userAuthRepository;
////	
////	@Bean
////	UserDetailsService userDetailsService() {
////		return new UserDetailsService() {
////			
////			@Override
////			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////				UserAuth auth = userAuthRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with username "+username));
////				return new CustomUserDetails(auth);
////			}
////		};
////	}
////	
////	@Bean
////	PasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
////	
////	@Bean
////	AuthenticationProvider authenticationProvider() {
////		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
////		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////		return daoAuthenticationProvider;
////	}
////	
////	@Bean
////	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////		return authenticationConfiguration.getAuthenticationManager();
////	}
////	
////	@Bean
////	static RoleHierarchy roleHierarchy() {
////		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
////		hierarchy.setHierarchy(Role.ADMIN+" > "+Role.INSTRUCTOR+"\n"+Role.INSTRUCTOR+" > "+Role.STUDENT);
////		return hierarchy;
////	}
////
////	
////}
//
//package com.cryptosoft.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class ApplicationConfig {
//
//    private final UserAuthRepository userAuthRepository;
//
//    public ApplicationConfig(UserAuthRepository userAuthRepository) {
//        this.userAuthRepository = userAuthRepository;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userAuthRepository.findByEmail(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//package com.cryptosoft.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import java.util.List;
//
//@Configuration
//public class ApplicationConfig {
//
//    private final AuthenticationProvider authenticationProvider;
//
//    public ApplicationConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return new ProviderManager(List.of(authenticationProvider));
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//package com.cryptosoft.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class ApplicationConfig {
//
//    private final AuthenticationProvider authenticationProvider;
//
//    public ApplicationConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//package com.cloud.ChronoSyncPro.config;
//
//import org.springframework.context.annotation.Bean;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.cloud.ChronoSyncPro.entity.Role;
//import com.cloud.ChronoSyncPro.entity.UserAuth;
//import com.cloud.ChronoSyncPro.repository.UserAuthRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//
//	private final UserAuthRepository userAuthRepository;
//	
//	@Bean
//	UserDetailsService userDetailsService() {
//		return new UserDetailsService() {
//			
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				UserAuth auth = userAuthRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with username "+username));
//				return new CustomUserDetails(auth);
//			}
//		};
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//	
//	@Bean
//	static RoleHierarchy roleHierarchy() {
//		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//		hierarchy.setHierarchy(Role.ADMIN+" > "+Role.INSTRUCTOR+"\n"+Role.INSTRUCTOR+" > "+Role.STUDENT);
//		return hierarchy;
//	}
//
//	
//}
package com.cloud.ChronoSyncPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloud.ChronoSyncPro.entity.Role;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

@Configuration
public class ApplicationConfig {

    private final UserAuthRepository userAuthRepository;

    public ApplicationConfig(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }
    
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserAuth auth = userAuthRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
                return new CustomUserDetails(auth);
            }
        };
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(Role.ADMIN + " > " + Role.INSTRUCTOR + "\n" + Role.INSTRUCTOR + " > " + Role.STUDENT);
        return hierarchy;
    }
}
