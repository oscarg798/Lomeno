import org.gradle.api.publish.PublishingExtension
import com.oscarg798.lomeno.LibraryConstants

apply(plugin = "maven-publish")

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(project.the<SourceSetContainer>()["main"].allSource)
    from("LICENCE.md") {
        into("META-INF")
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(LibraryConstants.publicationName) {
            groupId = LibraryConstants.artifactGroup
            artifactId = LibraryConstants.artifactName
            version = LibraryConstants.artifactVersion
            from(components["java"])
            artifact(sourcesJar)

            pom.withXml {
                asNode().apply {
                    appendNode("packaging", "jar")
                    appendNode("description", LibraryConstants.pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", LibraryConstants.pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", LibraryConstants.pomLicenseName)
                        appendNode("url", LibraryConstants.pomLicenseUrl)
                        appendNode("distribution", LibraryConstants.pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", LibraryConstants.pomDeveloperId)
                        appendNode("name", LibraryConstants.pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", LibraryConstants.pomScmUrl)
                    }
                }
            }
        }
    }

}

