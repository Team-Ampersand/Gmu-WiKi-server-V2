package team.ampersand.gmuwiki.global.r2dbc.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.nio.ByteBuffer
import java.util.UUID

@ReadingConverter
class ByteArrayToUUIDConverter : Converter<ByteArray, UUID> {

    override fun convert(source: ByteArray): UUID =
        ByteBuffer.wrap(source).run {
            UUID(this.long, this.long)
        }
}