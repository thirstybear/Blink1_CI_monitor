# Blink(1) CI monitor
A CI monitor using the Blink(1) light that I knocked up in Java. 

Note - currently it only understands Jenkins CI servers, but the design should be modular enough to plug in others.

At the moment, it uses direct command line calls to `blink1-tool`, so you need that installed and on the command path. If someone can help work out how to use the native Blink(1) Java library then do drop me a line - I could not get it to work.

Syntax:
`java -jar build/libs/blink1jenkins-1.0.0.jar <URL of the Jenkins job/view>`
