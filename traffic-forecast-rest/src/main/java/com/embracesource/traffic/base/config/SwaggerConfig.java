package com.embracesource.traffic.base.config;

import com.embracesource.traffic.base.exeception.ErrorEnums;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Swagger2的接口配置
 *
 * @author ruoyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final static String PACKAGE_REGEX = ";";
    /**
     * 是否开启swagger
     */
    @Value("${swagger.enabled:true}")
    private boolean enabled;
    /**
     * 设置请求的统一前缀
     */
    @Value("${swagger.pathMapping:/}")
    private String pathMapping;

    @Value("${swagger.basePackage:com.embracesource.traffic.forecast.web}")
    private String swaggerBasePackage;
    /**
     * 扫描类型
     * annotation   包含  ApiOperation 注解
     * <p>
     * package      依据base-package扫描
     * <p>
     * all          扫描所有
     */
    @Value("${swagger.type:all}")
    private String type;

    private static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)).orElse(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(PACKAGE_REGEX)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // 是否启用Swagger
                .enable(enabled)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo());
        // 设置哪些接口暴露给Swagger展示
        ApiSelectorBuilder select = docket.select();
        if ("package".equals(type)) {
            // 扫描指定包中的swagger注解
            select.apis(basePackage(swaggerBasePackage));
        } else if ("all".equals(type)) {
            // 扫描所有
            select.apis(RequestHandlerSelectors.any());
        }
        select.paths(PathSelectors.any())
                .build();
        /* 设置安全模式，swagger可以设置访问token */
        docket.securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(pathMapping);

        /**
         * 设置全局响应码
         */
        List<ResponseMessage> responseMessageList = globalResponseMessage();
        docket.globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList);
        return docket;
    }

    private List<ResponseMessage> globalResponseMessage() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(ErrorEnums.values()).forEach(errorEnums -> {
            responseMessageList.add(
                    new ResponseMessageBuilder()
                            .code(errorEnums.getCode())
                            .message(errorEnums.getMsg())
                            .build()
            );
        });
        return responseMessageList;
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("车流预测数据接口文档")
                // 描述
                .description("车流预测数据提供接口")
                // 版本
                .version("版本：0.0.1")
                .build();
    }
}
