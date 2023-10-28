#Integration backend and frontend test as one file
.DEFAULT_GOAL := run

BackendTests.class: BackendTests.java
	javac -cp .:../junit5.jar BackendTests.java

run: BackendTests.class
	java -jar ../junit5.jar -cp . -c BackendTests

clean:
	rm -f *.class

