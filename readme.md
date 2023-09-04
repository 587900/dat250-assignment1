# DAT250: Assignment 1
By 587900 (Kjetil Berg)

### Dockerhub repository
https://hub.docker.com/repository/docker/587900/dat250-assignment1/general

### Description
The instructions of https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md was followed to publish a simple REST API to dockerhub. It is a simple unit conversion app.
Everything went according to the instructions besides what is mention under the 'Trials and tribulations' section (problems and noteworthy moments).

### Trials and tribulations
1. During `gradle init` the inputted package name was invalid. The package name was `no.hvl.dat250.587900.assignment1`. The solution was to add an 'h' to my number, so the resulting package name was `no.hvl.dat250.h587900.assignment1`.
2. I set the target java version to v11, as it is the version we will use for the course.
3. Compilation errors were expected and encountered, and fixed according to the instructions (dependency and package in `App.java`).
4. I found out that 30 feet is 9.144 meters. This is correct.
5. I got a suggestion in my console, as the instructions indicated, so I added the dependency ('implementation') to my `build.gradle.kts` file:
```
Javalin: It looks like you don't have a logger in your project.
The easiest way to fix this is to add 'slf4j-simple':

pom.xml:
<dependency>
<groupId>org.slf4j</groupId>
<artifactId>slf4j-simple</artifactId>
<version>2.0.7</version>
</dependency>

build.gradle or build.gradle.kts:
implementation("org.slf4j:slf4j-simple:2.0.7")
```
6. When I tried running `podman login -u <username> docker.io`, it did not work (invalid reference).
After a bit of searching, I used the `-p` flag to specify the password, and it worked. The resulting command was
`podman login -u <username> -p <password> docker.io`.


### Project validations
After making sure all of the relevant tools were installed by using various `--version` commands, I continued with the instructions.
1. I am very used to working with git, so creating a repository was simple. I checked GitHub after the fact, and everything is commited and thus working fine.
2. I ran the specified gradle commands and they worked. I also built the project using gradle, and saw the relevant changes in the `build` directory. Later in the project I used gradle to run the application, meaning gradle must have been set up correctly.
3. I fixed the mentioned compilation errors and the application ran as expected.
4. I refactored the code to use a 'Converter' class and wrote tests for it. The tests passed and the application worked as expected when testing it manually.
5. I created a dockerfile and built the image. I uploaded it to DockerHub and went to see that it was listed as on the website. This means the upload must have been successful.

### Trying to run the image
I also ran the image, but I encountered some problems:
`gradlew` was not found when I tried running the image. Adjusting the Dockerfile's last statement to use double-quotes instead of single-quotes (`CMD ["./gradlew", "run"]` instead of `CMD ['./gradlew', 'run']`) worked for me.
I did not push this to GitHub or change it for the DockerHub image, because I imagine it is a Windows issue (not sure though).\
To access the web server I needed to expose the port. I did this by using the `-p` flag as such:
`podman run -p 9000:9000 unit-converter` (maps port 9000 on the host to port 9000 on the container).
