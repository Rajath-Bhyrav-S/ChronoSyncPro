//package com.cryptosoft;

//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.cryptosoft.entity.Role;
//import com.cryptosoft.entity.UserAuth;
//import com.cryptosoft.repository.UserAuthRepository;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//
//@SpringBootApplication
//@RequiredArgsConstructor
//public class AmsApiV2Application {
//	
//	private final UserAuthRepository userAuthRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public static void main(String[] args) {
//		SpringApplication.run(AmsApiV2Application.class, args);
//	}
//	
//	@PostConstruct
//	public void createAdmin() {
//		
//		if (userAuthRepository.findByEmail("admin@cryptosoft.com").isEmpty()) {
//			// @formatter:off
//			UserAuth admin = UserAuth.builder()
//					.email("admin@cryptosoft.com")
//					.password(passwordEncoder.encode("admin123"))
//					.role(Role.ADMIN)
//					.build();
//			// @formatter:on
//			userAuthRepository.save(admin);
//		}
//		
//	}
//	
//}
//package com.example.ams;
//package com.cryptosoft;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class AmsApiV2Application {
//    public static void main(String[] args) {
//        SpringApplication.run(AmsApiV2Application.class, args);
//    }
//}
package com.cloud.ChronoSyncPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloud.ChronoSyncPro.entity.Role;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AmsApiV2Application {
    
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public AmsApiV2Application(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(AmsApiV2Application.class, args);
    }
    
    @PostConstruct
    public void createAdmin() {
        
        if (userAuthRepository.findByEmail("admin@cryptosoft.com").isEmpty()) {
            UserAuth admin = UserAuth.builder()
                    .email("admin@cryptosoft.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userAuthRepository.save(admin);
        }
        
    }
}

    

