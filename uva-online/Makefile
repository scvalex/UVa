include problem.mk
all: usage

init-java:
	cp -R empty-java/ $P
	cd $P && touch $P.1.in && touch $P.1.out
	
usage:
	@echo "usage: make init-java P=<PROBLEM_NUMBER>"
	
tabs-to-spaces:
	find . -name "*.java" -exec sh -c "expand {} > tmp && mv tmp {}" \;

tests:
	@ echo "Running tests for all problems. This may take a while..."
	find . -type d -maxdepth 1 -not -regex ".*empty-java\|.*empty-cpp" \
		-exec sh -c "cd {} && make test" \;
