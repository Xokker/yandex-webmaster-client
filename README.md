Repository management:
```xml
<repository>
    <id>yandex-webmaster-mvn-repo</id>
    <url>https://raw.github.com/Xokker/yandex-webmaster-client/mvn-repo/</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
```

Dependency management:
```xml
<dependency>
    <groupId>ru.webeffector</groupId>
    <artifactId>webmaster-client</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>
```