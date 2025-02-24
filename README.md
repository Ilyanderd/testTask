Проект следует запускать либо из Intellij IDEA с указанием дополнительных атрибутов до запуска, либо через командную строку с использованием последовательности команд:
1. Зайти в директорию проекта: cd путь/до/проекта;
2. Собрать проект: mvn clean install;
3. Запустить проект: mvn exec:java -Dexec.mainClass="com.ilyanders.Main" -Dexec.args="Здесь следует писать аргументы, в том числе входящие файлы".

В проект добавлены примеры входных файлов из задания.

Версия Java: 17;
Версия Maven: 3.9.8;
Сторонние библиотеки:
<dependencies>
        <!-- https://mvnrepository.com/artifact/commons-cli/commons-cli -->
        <dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>1.9.0</version>
        </dependency>
    </dependencies>
