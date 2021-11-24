
TARGETS = $(basename $(wildcard *.java))
API=../API

.PHONY: help ${TARGETS}

help:
	@echo "Usage: make ${subst $() $(), | ,$(TARGETS)}"
	@echo "This copies all the Java API files, and the target Java file,"
	@echo "to the directory 'build', compiles everything and then runs the target."

clean:
	rm -rf build

${TARGETS}: 
	rm -rf build && mkdir build
	cp ${API}/*.java $@.java build/
	( cd build/ ; javac *.java ; java $@ )
