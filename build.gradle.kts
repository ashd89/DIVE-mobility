plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//kotlin-logging
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.12")

	// Persistent (RDB / Cache)
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

	// POI (엑셀)
	implementation("org.apache.poi:poi-ooxml:5.2.5")

	//web client (api 요청)
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// Hibernate Spatial + JTS
	implementation("org.hibernate.orm:hibernate-spatial")
	implementation("org.locationtech.jts:jts-core:1.19.0")

	// PostGIS JDBC 확장
	implementation("net.postgis:postgis-jdbc:2023.1.0")

	// JSON 직렬화
	implementation("org.n52.jackson:jackson-datatype-jts:1.2.10")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
