package team.ampersand.gmuwiki.global.r2dbc

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import team.ampersand.gmuwiki.global.r2dbc.converter.ByteArrayToUUIDConverter
import team.ampersand.gmuwiki.global.r2dbc.converter.UUIDToByteArrayConverter

@Configuration
class R2dbcConfig(
    private val connectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {

    @Bean
    fun init() = ConnectionFactoryInitializer().apply {
        setConnectionFactory(connectionFactory)
        setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
    }


    override fun connectionFactory() = connectionFactory
    override fun getCustomConverters() = listOf(UUIDToByteArrayConverter(), ByteArrayToUUIDConverter())
}