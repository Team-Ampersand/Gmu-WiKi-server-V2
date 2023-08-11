package team.ampersand.gmuwiki.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.*
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.ampersand.gmuwiki.common.annotation.ReadOnlyUseCase
import team.ampersand.gmuwiki.common.annotation.UseCase

@Configuration
@ComponentScan(
    basePackages = ["team.ampersand.gmuwiki"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class,
                ReadOnlyUseCase::class
            ]
        )
    ]
)
class ComponentScanConfig