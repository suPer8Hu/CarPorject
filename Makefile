#Integration backend and frontend test as one file
.DEFAULT_GOAL := Integration

BackendTests.class: BackendTests.java
	javac -cp .:../junit5.jar BackendTests.java

runBDTest: BackendTests.class
	java -jar ../junit5.jar -cp . -c BackendTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java

runFDTest: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

Integration: runBDTest runFDTest
clean:
	rm -f *.class

