package com.mandiri.umkm.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Pembukuan Umkm",
        version = "1.0",
        contact = @Contact(
                name = "Project Github",
                url = "https://github.com/masumrpg/pembukuan-umkm-dan-pinjaman-modal"
        )
)
)
public class OpenAPIConfiguration implements WebMvcConfigurer {
}