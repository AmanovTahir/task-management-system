package com.company.taskms.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(lazyInit = true)
//@ContextConfiguration()
@ContextConfiguration(initializers = DBContainerInitializer.class)
public class TestCore {

    @Autowired
    public MockMvc mockMvc;

    protected Jwt getJwt() {
        return Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", "simple@email.com")
                .claim("scope", "read")
                .build();
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor mockJwt() {
        return jwt().jwt(getJwt());
    }

}
