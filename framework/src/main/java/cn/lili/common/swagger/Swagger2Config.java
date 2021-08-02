package cn.lili.common.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chopper
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${swagger.contact.name}")
    private String name;

    @Value("${swagger.contact.url}")
    private String url;

    @Value("${swagger.contact.email}")
    private String email;

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "accessToken", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    @Bean
    public Docket goodsAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-商品")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
//               .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.goods"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket goodsBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端端-商品")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
//               .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.goods"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket goodsStoreRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-商品")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
//               .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.goods"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket memberBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-会员")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.member"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket memberAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-会员")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.member"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket memberSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-会员")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.member"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket promotionBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-促销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.promotion"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket promotionAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.promotion"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket promotionSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-促销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.promotion"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket storeAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-店铺")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.store"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket storeBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-店铺")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.store"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket tradeBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-交易")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.trade"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket tradeSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-交易")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.trade"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket tradeAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-交易")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.trade"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket settingSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-设置")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.setting"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket settingAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-设置")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.setting"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket permissionAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-权限")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.permission"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }



    @Bean
    public Docket otherBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-其他")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.other"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket otherRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-其他")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.other"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket otherAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-其他")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.other"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket commonRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.common"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket distributionBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-分销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.distribution"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket distributionAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-分销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.distribution"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket distributionSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-分销")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.distribution"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket statisticsAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-统计")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.statistics"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket statisticsRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-统计")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.statistics"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket paymentBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-支付")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.payment"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket passportBuyerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员端-登录")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.buyer.passport"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket passportSellerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商家端-登录")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.seller.passport"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket passportAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端-登录")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("cn.lili.controller.admin.passport"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(name, url, email))
                .version(version)
                .build();
    }
}
