# TypeSpeeder

The warnings are written in english and make sure that you read the warnings carefully.

## Update 1 of 3
One file is now added to the project:

* MenuTest.java



## Update 2 of 3
Three files are now added to the project:

* ChallengePerformanceTest.java
* ChallengeTest.java
* MenuPerformanceTest.java

## Update 3 of 3
Two files are now added to the project:

* NewsLetterTest.java
* PatchTest.java




 
Profilering gällande version 0.2.0 påvisar den metod som belastar programmet bortom själva Application Spring Boot är då metoden checkCredentials som drar runt 4 procent av CPU och utgörs på 300 ms. 
 ![image](https://github.com/Kerem1989/TypeSpeeder/assets/98690898/84ae3470-6d2b-47ce-89e5-e80233499921)

Profilering gällande version 0.3.0 påvisar en fortsatt belastning från checkCredentials dock med en smärre minskning till 3 procent av CPU och utförs på 235 ms. 
 ![image](https://github.com/Kerem1989/TypeSpeeder/assets/98690898/ccc736a4-2b65-4ad2-bb36-656d3e3a417d)

Profilering gällande version 0.4.1 introducerar en ny bov i dramat. 2 procent av CPU går till metoden som beräknar, detta är förväntat då spelets logik har utvecklats en aning, med detta tillkommer nya beräkningsparametrar. 
 ![image](https://github.com/Kerem1989/TypeSpeeder/assets/98690898/35f17a6b-640a-4df5-9bb1-16d4c573623e)

Profilering gällande version 0.6.1 påvisar en ny metod som träder fram som en CPU slukare, vilket är metoden där man lägger till spelare. 
 ![image](https://github.com/Kerem1989/TypeSpeeder/assets/98690898/36ccc0ba-f837-459d-ac8b-088b745171c8)

Den första stabila versionen av programmet påvisar att metoderna CheckCredentials, metoden som beräknar poäng och editUser drar mest i själva körningen, personligen hade jag på förhand förväntat att själva spelet i sig skulle dra tex mer än metoden editUser. 
![image](https://github.com/Kerem1989/TypeSpeeder/assets/98690898/dd8267f3-db72-40e0-9a7a-5d3880e7718f)
