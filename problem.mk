TESTS ?=
OBJECTS := $(patsubst %.cpp, %.o, $(wildcard *.cpp))

PROBLEM := $(shell basename $$(pwd))

JAVA_SOURCES = $(wildcard *.java)
ifneq "$(JAVA_SOURCES)" ""
TARGET := Main.class
RUN_COMMAND := java Main
endif

CXX_SOURCES = $(wildcard *.cpp)
ifneq "$(CXX_SOURCES)" ""
TARGET := $(PROBLEM)
RUN_COMMAND := ./$(TARGET)
$(TARGET): $(OBJECTS)
	g++ -Wall -O2 -lm $(OBJECTS) -o $(TARGET)
endif

all: $(TARGET)

.PHONY: all build test

.cpp.o:
	g++ -c -Wall $< -o $@

.SUFFIXES: .java .class
.java.class:
	javac $<

test: $(TARGET)
	mkdir -p test
	$(foreach i,$(TESTS),$(RUN_COMMAND) < $(PROBLEM).$(i).in > test/$(PROBLEM).$(i).out && diff $(PROBLEM).$(i).out test/$(PROBLEM).$(i).out)

clean:
	rm -rf test/ *.class *.o $(TARGET)
