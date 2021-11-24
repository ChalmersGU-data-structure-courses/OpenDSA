
TARGETS = $(basename $(wildcard *.py))
API=../API

.PHONY: help ${TARGETS}

help:
	@echo "Usage: make ${subst $() $(), | ,$(TARGETS)}"
	@echo "This copies all the Python API files, and the target Python file,"
	@echo "to the directory 'build', and then runs the target."

clean:
	rm -rf build

${TARGETS}: 
	rm -rf build && mkdir build
	cp ${API}/*.py $@.py build/
	( cd build/ ; python3 $@ )
