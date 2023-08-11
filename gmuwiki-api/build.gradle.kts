plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {

}

allOpen {
    annotation("team.ampersand.gmuwiki.common.annotation.UseCase")
    annotation("team.ampersand.gmuwiki.common.annotation.ReadOnlyUseCase")
}