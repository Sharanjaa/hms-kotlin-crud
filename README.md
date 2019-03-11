<h1>Hms-kotlin-crud</h1>

The HMS Kotlin CRUD is a modern web app written in kotlin and it provides working examples of how to integrate your app with ORM. Specifically, this sample application demonstrates the following:
<ul>
    <li>Create, Read, Query, Update, Delete entities.</li>
    <li>All operations are performed using Kotlin and Gradle.</li>
</ul>

<h2>Technologies used</h2>

<ul>
  <li>Language: Kotlin</li>
<li>Core framework: Spring Boot 2.0 with Spring Framework Kotlin support</li>
<li>Web framework: Spring MVC</li>
<li>Templates: Thymeleaf </li>
<li>Persistence : Spring Data JPA</li>
<li>Databases: MySQL 8</li>
<li>Build: Gradle Script with the Kotlin DSL</li>
<li>Testing: Junit 5, Mockito and AssertJ</li>
</ul>

<h2>Running hms-kotlin-crud locally</h2>
<code>
	git clone https://github.com/Sharanjaa/hms-kotlin-crud.git </code>
	<code>cd hms-kotlin-crud</code>
	<code>./gradlew bootRun
</code>

<h2>Produce Coverage Reports</h2>

<h4>Add Jacoco plugin in Build.Gradle</h4>

JaCoCo is a free Java/Kotlin code coverage library distributed under the Eclipse Public License.

<code>
apply plugin: 'jacoco'
</code>


<code>
test {
    useJUnitPlatform()
    jacoco {
        destinationFile = file("${buildDir}/jacoco/test.exec")
    }
}
	
</code>


<code>
jacoco {
    // You may modify the Jacoco version here
    toolVersion = "0.8.2"
}

</code>

<code>

jacocoTestReport {
    // Adjust the output of the test report
    reports {
        xml.enabled true
        csv.enabled false
    }
 }
</code>

We got everything set up. We have a working Kotlin Project with JUnit 5. After executing the test task (also included during build task, by default), execute the jacocoTestReport task. If it succeeds, reports are available under <b>build/reports/jacoco/test</b>. Our configuration generates a HTML report and a XML report.

<a href="https://ibb.co/SPTQ8Ny"><img src="https://i.ibb.co/s2S5GjR/Screenshot-from-2019-03-11-11-35-14.png" alt="Screenshot-from-2019-03-11-11-35-14" border="0"></a>
