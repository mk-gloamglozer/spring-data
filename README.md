# Spring-DI
Update of the OOP demo to use spring for dependancy injection and to use JPA and Hibernate with in memory database to store the dice

The program is designed to take inputs from the command line and Roll Dice. Via the interface it is possible to add and remove named dice using archetype templates, and choose multiple dice to roll. The roller will give the sum of the dice rolled. 

If maven is installed it can be run with the command ```mvn spring-boot:run```. Alternatively if you wish to build a jar file run ```mvn clean package spring-boot:repackage```

