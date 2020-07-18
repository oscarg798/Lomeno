import com.oscarg798.lomeno.BintrayConstanst
import  com.oscarg798.lomeno.LibraryConstants

apply {
    from("install.gradle.kts")
}

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.jfrog.bintray") version "1.8.5"
}


bintray {
    user = "oscarg798"
    key = "6cbc9cf7037ac1f1459c9ee452e82da202229606"
    publish = true

    setPublications(LibraryConstants.publicationName)

    pkg.apply {
        repo = BintrayConstanst.repoName
        name = LibraryConstants.artifactGroup
        userOrg = BintrayConstanst.userOrg
        githubRepo = BintrayConstanst.githubRepo
        vcsUrl = BintrayConstanst.githubRepo
        description = LibraryConstants.pomDesc
        setLabels("kotlin")
        setLicenses(BintrayConstanst.license)
        desc = LibraryConstants.pomDesc
        websiteUrl = BintrayConstanst.githubRepo
        issueTrackerUrl = BintrayConstanst.githubRepo
        githubReleaseNotesFile = BintrayConstanst.githubRepo

        version.apply {
            name = LibraryConstants.artifactVersion
            desc = LibraryConstants.pomDesc
            vcsTag = "v${LibraryConstants.artifactVersion}"
        }
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib", "1.3.72"))
    implementation("com.squareup.okhttp3:okhttp:4.8.0")

    testImplementation("junit:junit:4.12")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("org.amshove.kluent:kluent:1.4")
}
