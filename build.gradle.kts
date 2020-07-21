import com.oscarg798.lomeno.BintrayConstanst
import com.oscarg798.lomeno.LibraryConstants
import com.oscarg798.lomeno.Dependencies
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

apply {
    from("install.gradle.kts")
}

plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("com.jfrog.bintray") version "1.8.5"
}

val properties: Properties = Properties()
properties.load(FileInputStream("local.properties"))


bintray {
    user = properties.getProperty(BintrayConstanst.BINTRAY_USER_KEY)
    key = properties.getProperty(BintrayConstanst.BINTRAY_PASSWORD_KEY)
    publish = true

    setPublications(LibraryConstants.PUBLICATION_NAME)

    pkg.apply {
        repo = BintrayConstanst.REPO_NAME
        name = LibraryConstants.ARTIFACT_GROUP
        userOrg = BintrayConstanst.USER_ORG
        githubRepo = BintrayConstanst.GITHUB_URL
        vcsUrl = BintrayConstanst.GITHUB_URL
        description = LibraryConstants.POM_DESCRIPTION
        setLabels("kotlin")
        setLicenses(BintrayConstanst.LICENSE)
        desc = LibraryConstants.POM_DESCRIPTION
        websiteUrl = BintrayConstanst.GITHUB_URL
        issueTrackerUrl = BintrayConstanst.GITHUB_URL
        githubReleaseNotesFile = BintrayConstanst.GITHUB_URL

        version.apply {
            name = LibraryConstants.VERSION
            desc = LibraryConstants.POM_DESCRIPTION
            vcsTag = "v${LibraryConstants.VERSION}"
        }
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib", "1.3.72"))
    implementation(Dependencies.OK_HTTP)

    testImplementation(Dependencies.JUNIT)
    testImplementation(Dependencies.MOCKK)
    testImplementation(Dependencies.KLUENT)
}
