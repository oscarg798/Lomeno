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
        create<MavenPublication>(LibraryConstants.PUBLICATION_NAME) {
            groupId = LibraryConstants.ARTIFACT_GROUP
            artifactId = LibraryConstants.ARTIFACT_NAME
            version = LibraryConstants.VERSION
            from(components["java"])
            artifact(sourcesJar)

            pom.withXml {
                asNode().apply {
                    appendNode("packaging", "jar")
                    appendNode("description", LibraryConstants.POM_DESCRIPTION)
                    appendNode("name", rootProject.name)
                    appendNode("url", LibraryConstants.POM_URL)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", LibraryConstants.LICENSE_NAME)
                        appendNode("url", LibraryConstants.LICENSE_URL)
                        appendNode("distribution", LibraryConstants.LICENSE_DIST)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", LibraryConstants.DEVELOPER)
                        appendNode("name", LibraryConstants.DEVELOPER_NAME)
                    }
                    appendNode("scm").apply {
                        appendNode("url", LibraryConstants.POM_SRC_URL)
                    }
                }
            }
        }
    }

}

