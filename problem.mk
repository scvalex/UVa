TESTS ?=
OBJECTS := $(patsubst %.cpp, %.o, $(wildcard *.cpp))

TARGET := $(shell basename $$(pwd))

JAVA_SOURCES = $(wildcard *.java)
ifneq "$(JAVA_SOURCES)" ""
TARGET := $(TARGET).class
endif

CXX_SOURCES = $(wildcard *.cpp)
ifneq "$(CXX_SOURCES)" ""
$(TARGET): $(OBJECTS)
	g++ -Wall -O2 -lm $(TARGET).cpp -o $(TARGET)
endif

all: $(TARGET)

.PHONY: all build test

.cpp.o:
	g++ -Wall $< -o $@

.SUFFIXES: .java .class
.java.class:
	javac $<

test: build
	mkdir -p test
	$(foreach i,$(TESTS),./$(TARGET) < $(TARGET).$(i).in > test/$(TARGET).$(i).out && diff $(TARGET).$(i).out test/$(TARGET).$(i).out)

clean:
	rm -rf test/ *.class *.o $(TARGET)
