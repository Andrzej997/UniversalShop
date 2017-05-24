package com.dummy.universalshop.filter;

import com.dummy.universalshop.model.JwtTokenPojo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTokenEndpointAuthFilter extends TokenEndpointAuthenticationFilter {

    /**
     * @param authenticationManager an AuthenticationManager for the incoming request
     * @param oAuth2RequestFactory
     */
    public CustomTokenEndpointAuthFilter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        super(authenticationManager, oAuth2RequestFactory);
    }

    @Override
    protected Authentication extractCredentials(HttpServletRequest request) {
        Authentication result = null;

        String grantType = request.getParameter("grant_type");
        if (grantType != null && grantType.equals("password")) {
            result = super.extractCredentials(request);
        } else if (grantType != null && grantType.equals("refresh_token")) {

            Jwt jwtToken = JwtHelper.decode(request.getParameter("refresh_token"));
            String claims = jwtToken.getClaims();

            ObjectMapper objectMapper = new ObjectMapper();
            JwtTokenPojo tokenClaims;
            try {
                tokenClaims = objectMapper.readValue(claims, JwtTokenPojo.class);
            } catch (Exception ex) {
                throw new BadCredentialsException("Error parsing JWT token", ex);
            }

            List<GrantedAuthority> authorities = tokenClaims.getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            result = new UsernamePasswordAuthenticationToken(tokenClaims.getClient_id(), "", authorities);
        }
        return result;
    }
}
