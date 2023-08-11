package team.ampersand.gmuwiki.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.ampersand.gmuwiki.global.security.SecurityProperties

@ConfigurationPropertiesScan(
    basePackageClasses = [
        SecurityProperties::class
    ]
)
@Configuration
class PropertiesScanConfig