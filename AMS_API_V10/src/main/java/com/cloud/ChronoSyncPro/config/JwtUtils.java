////package com.cryptosoft.config;
////
////import java.util.Date;
////import java.util.HashMap;
////import java.util.Map;
////import java.util.function.Function;
////
////import javax.crypto.SecretKey;
////
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Component;
////
////import com.cryptosoft.entity.UserAuth;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.io.Decoders;
////import io.jsonwebtoken.security.Keys;
////
////@Component
////public class JwtUtils {
////
////	//5 Minutes Expiration
////	@Value("${jwt_token.expiration_time}")
////	private long EXPIRATION_TIME;
////	
////	@Value("${jwt.signing.secret}")
////	private String SECRET;
////	
////	public String extractUsername(String jwtToken) {
////		return extractClaims(jwtToken,Claims::getSubject);
////	}
////
////	private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
////		final Claims claims=extractAllClaims(jwtToken);
////		return claimsResolver.apply(claims);
////	}
////
////	private Claims extractAllClaims(String jwtToken) {
////	// @formatter:off
////		return Jwts
////				.parser()
////				.verifyWith(getSigningKey())
////				.build()
////				.parseSignedClaims(jwtToken)
////				.getPayload();
////	// @formatter:on
////	}
////
////	private SecretKey getSigningKey() {
////		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
////		return Keys.hmacShaKeyFor(keyBytes);
////	}
////
////	public boolean isTokenValid(String jwtToken, UserDetails user) {
////		final String username=extractUsername(jwtToken);
////		return (username.equals(user.getUsername())&& !isTokenExpired(jwtToken));
////	}
////
////	private boolean isTokenExpired(String jwtToken) {
////		return extractExpiration(jwtToken).before(new Date());
////	}
////
////	private Date extractExpiration(String jwtToken) {
////		return extractClaims(jwtToken, Claims::getExpiration);
////	}
////
////	public String generateToken(UserAuth userAuth) {
////		return generateToken(new HashMap<>(),userAuth);
////	}
////	public String generateToken(Map<String, Object> extraClaims,UserAuth userAuth) {
////	// @formatter:off
////		return Jwts
////				.builder()
////				.claims(extraClaims)
////				.subject(userAuth.getEmail())
////				.issuedAt(new Date(System.currentTimeMillis()))
////				.expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
////				.signWith(getSigningKey(),Jwts.SIG.HS512)
////				.compact();
////	// @formatter:on
////
////	}
////	
////	
////
////}
//
//package com.cryptosoft.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//public class JwtUtils {
//    private static final String SECRET_KEY = "your-secret-key-here";
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//
//    private final SecretKey key;
//
//    public JwtUtils() {
//        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        return extractClaims(token).getExpiration().after(new Date());
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
//package com.cryptosoft.config;
//
//import io.jsonwebtoken.Claims;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.io.Decoders;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//public class JwtUtils {
//    private static final String SECRET_KEY = "your-secret-key-here";
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//
//    private final SecretKey key;
//
//    public JwtUtils() {
//        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//            .subject(userDetails.getUsername())
//            .issuedAt(new Date())
//            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(key)
//            .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        return extractClaims(token).getExpiration().after(new Date());
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parser()
//            .verifyWith(key)
//            .build()
//            .parseSignedClaims(token)
//            .getPayload();
//    }
//}
//package com.cryptosoft.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.io.Decoders;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//public class JwtUtils {
//
//    private static final String SECRET_KEY = "your-secret-key-here";
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//
//    private final SecretKey key;
//
//    public JwtUtils() {
//        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//            .setSubject(userDetails.getUsername())
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(key)
//            .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        return extractClaims(token).getExpiration().after(new Date());
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parser()
//            .setSigningKey(key)
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    }
//}
//package com.cryptosoft.config;
//
//import java.util.Date;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.cryptosoft.entity.UserAuth;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtils {
//
//	//5 Minutes Expiration
//	@Value("${jwt_token.expiration_time}")
//	private long EXPIRATION_TIME;
//	
//	@Value("${jwt.signing.secret}")
//	private String SECRET;
//	
//	public String extractUsername(String jwtToken) {
//		return extractClaims(jwtToken,Claims::getSubject);
//	}
//
//	private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
//		final Claims claims=extractAllClaims(jwtToken);
//		return claimsResolver.apply(claims);
//	}
//
//	private Claims extractAllClaims(String jwtToken) {
//	// @formatter:off
//		return Jwts
//				.parser()
//				.verifyWith(getSigningKey())
//				.build()
//				.parseSignedClaims(jwtToken)
//				.getPayload();
//	// @formatter:on
//	}
//
//	private SecretKey getSigningKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
//
//	public boolean isTokenValid(String jwtToken, UserDetails user) {
//		final String username=extractUsername(jwtToken);
//		return (username.equals(user.getUsername())&& !isTokenExpired(jwtToken));
//	}
//
//	private boolean isTokenExpired(String jwtToken) {
//		return extractExpiration(jwtToken).before(new Date());
//	}
//
//	private Date extractExpiration(String jwtToken) {
//		return extractClaims(jwtToken, Claims::getExpiration);
//	}
//
//	public String generateToken(UserAuth userAuth) {
//		return generateToken(new HashMap<>(),userAuth);
//	}
//	public String generateToken(Map<String, Object> extraClaims,UserAuth userAuth) {
//	// @formatter:off
//		return Jwts
//				.builder()
//				.claims(extraClaims)
//				.subject(userAuth.getEmail())
//				.issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
//				.signWith(getSigningKey(),Jwts.SIG.HS512)
//				.compact();
//	// @formatter:on
//
//	}
//	
//	
//
//}
//package com.cryptosoft.config;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.cryptosoft.entity.UserAuth;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtils {
//
//    //5 Minutes Expiration
//    @Value("${jwt_token.expiration_time}")
//    private long EXPIRATION_TIME;
//    
//    @Value("${jwt.signing.secret}")
//    private String SECRET;
//    
//    public String extractUsername(String jwtToken) {
//        return extractClaims(jwtToken, Claims::getSubject);
//    }
//
//    private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(jwtToken);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String jwtToken) {
//        return Jwts.parser()
//                .setSigningKey(getSigningKey())
//                .parseClaimsJws(jwtToken)
//                .getBody();
//    }
//
//    private SecretKey getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public boolean isTokenValid(String jwtToken, UserDetails user) {
//        final String username = extractUsername(jwtToken);
//        return (username.equals(user.getUsername()) && !isTokenExpired(jwtToken));
//    }
//
//    private boolean isTokenExpired(String jwtToken) {
//        return extractExpiration(jwtToken).before(new Date());
//    }
//
//    private Date extractExpiration(String jwtToken) {
//        return extractClaims(jwtToken, Claims::getExpiration);
//    }
//
//    public String generateToken(UserAuth userAuth) {
//        return generateToken(new HashMap<>(), userAuth);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserAuth userAuth) {
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userAuth.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSigningKey())
//                .compact();
//    }
//
//    // New method to generate token from UserDetails
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSigningKey())
//                .compact();
//    }
//}
package com.cloud.ChronoSyncPro.config;

import java.util.Date;

import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cloud.ChronoSyncPro.entity.UserAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.HashMap;


@Component
public class JwtUtils {

    // 5 Minutes Expiration
    @Value("${jwt_token.expiration_time}")
    private long EXPIRATION_TIME;
    
    @Value("${jwt.signing.secret}")
    private String SECRET;
    
    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String jwtToken, UserDetails user) {
        final String username = extractUsername(jwtToken);
        return (username.equals(user.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    public String generateToken(UserAuth userAuth) {
        return generateToken(new HashMap<>(), userAuth);
    }

    public String generateToken(Map<String, Object> extraClaims, UserAuth userAuth) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userAuth.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    // New method to generate token from UserDetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }
}
